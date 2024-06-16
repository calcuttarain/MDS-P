import requests

url = "http://localhost:8081/mds/api/user"
url1 = "http://localhost:8081/mds/api/patient"

#createPacientAccount, createDoctorAccount, authenticate sunt la url
#getDoctorsBySpecialization, requestAppointment sunt la url1
data_register_patient_example = {
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

# Definirea datelor pentru funcția createDoctorAccount
data_register_doctor_example = {
    "function": "createDoctorAccount",
    "parameters": {
        "first_name": "John",
        "last_name": "Doe",
        "email": "johndoe@example.com",
        "password": "secure_password",
        "phone": "+123456789",
        "specialization": "Cardiology",
        "description": "Experienced cardiologist",
        "office_id": 1
    }
}

data_authenticate_example = {
    "function": "authenticate",
    "parameters": {
        "email": "john.doe@example.com",
        "password": "password1234"
    }
}

data_getDoctorsBySpecialization_example = {
    "function": "getDoctorsBySpecialization",
    "parameters": {
        "specialization": "Pediatrie"
    }
}

data_requestAppointment_example = {
    "function": "requestAppointment",
    "parameters": {
        "patient_id": "16",
        "specialization": "Pediatrie",
        "date_string": "2026 12 30 15:30",
        "notes": "va fi super"
    }
}


#posting example
response = requests.post(url1, json=data_requestAppointment_example)

if response.status_code == 200:
    response_data = response.json()
    print("Response:", response_data)
else:
    print("Failed to execute function, status code:", response.status_code)
    

