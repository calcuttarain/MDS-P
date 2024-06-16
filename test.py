import requests

url = "http://localhost:8081/mds/api/user"

# Datele pentru cerere
data = {
    "function": "createPacientAccount",
    "parameters": {
        "first_name": "Johnny",
        "last_name": "Doe",
        "email": "john.doe@example.com",
        "password": "password123",
        "phone": "1234567890",
        "medical_history": "None",
        "allergies": "Peanuts",
        "blood_type": "O+"
    }
}

data1 = {
    "function": "authenticate",
    "parameters": {
        "email": "elena.constantinescu@example.com",
        "password": "parola123"
    }
}

response = requests.post(url, json=data1)

if response.status_code == 200:
    response_data = response.json()
    print("Response:", response_data)
else:
    print("Failed to execute function, status code:", response.status_code)
