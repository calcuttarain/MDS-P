import requests
import json
from instructions import *

class OllamaLlamaModel:
    def __init__(self, role, instructions, temperature=0.5):
        self.base_url = "http://localhost:11434"
        self.model = "llama3:8b-instruct-q8_0"
        self.temperature = temperature
        self.messages = [{"role": role, "content": instructions}]
    
    def update_instructions(self, new_instructions, data=None):
        print(self.messages)
        last_message = self.messages[-2]
        print("lastt measje:::", last_message)  # Preserve the last message
        if data:
            self.messages = [{"role": "system", "content": format_instructions(data)}, last_message]
        else:
            self.messages = [{"role": "system", "content": new_instructions}, last_message]
            print("self.messages:::", self.messages)

    def send_message(self, user_input):
        self.messages.append({"role": "user", "content": user_input})
        response = requests.post(
            f"{self.base_url}/api/chat",
            json={
                "model": self.model,
                "messages": self.messages,
                "format": "json",
                "options": {"temperature": self.temperature},
                "stream": False
            }
        )
        
        if response.status_code == 200:
            result = response.json()
            assistant_message = result.get("message", {}).get("content", "")
            assistant_message_json = json.loads(assistant_message)
            self.messages.append({"role": "assistant", "content": assistant_message_json.get("user_message", "")})
            return assistant_message
        else:
            return f"Error: {response.status_code} - {response.text}"

model = OllamaLlamaModel(role="system", instructions=general_instructions)

def handle_response(response, user_input):
    try:
        response_json = json.loads(response)
        flag = response_json.get('flag')
        print("flag::::", flag)
        message = response_json.get('message')
        if flag == "failed":
            model.update_instructions(general_instructions)
            response = model.send_message(user_input)
            return response
        elif flag == "registration":
            model.update_instructions(registration_instructions)
            response = model.send_message(user_input)
            return response
        elif flag == "appointment":
            model.update_instructions(appointment_instructions)
            response = model.send_message(user_input)
            return response
        elif flag == "login":
            model.update_instructions(login_instructions)
            response = model.send_message(user_input)
            return response
        elif flag == "login_success":
            email = response_json["email"]
            password = response_json["password"]
            data_authenticate= {
                "function": "authenticate",
                "parameters": {
                    "email": email,
                    "password": password
                    }
                }

            data = requests.post(url_user, json=data_authenticate)
            if data.status_code == 200:
                data_json = data.json()
                print("data::::", data)
                model.update_instructions(logged_in_instructions, data_json)
                response = model.send_message(user_input)
                return response
        return response
    except json.JSONDecodeError:
        return "A apărut o eroare în interpretarea răspunsului."



url_user = "http://localhost:8081/mds/api/user"
url_patient = "http://localhost:8081/mds/api/patient"

# while True:
#     user_input = input("User: ")
#     response = model.send_message(user_input)
#     message_to_user = handle_response(response, user_input)
#     print("messages::::", message_to_user)
#     print(f"Assistant: {' '.join(json.loads(message_to_user).get('user_message', '').split())}")