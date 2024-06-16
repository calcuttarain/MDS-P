# main.py

import sys
import json
from PyQt5.QtWidgets import QApplication, QWidget, QVBoxLayout, QTextEdit, QLineEdit, QPushButton, QCheckBox
from PyQt5.uic import loadUi
from PyQt5.QtCore import QFile, QTextStream
from model import model, handle_response

class LaunchInterface(QWidget):
    def __init__(self):
        super().__init__()
        
        self.initUI()
        
    def initUI(self):
        loadUi('launch_interface.ui', self)
        
        self.launch_button.clicked.connect(self.launch_chat)
        
        # Apply CSS styling
        self.setStyleSheet("""
            QWidget {
                background-color: #2b2b2b;
                color: #ffffff;
                font-family: 'Arial', sans-serif;
            }
            QCheckBox {
                background-color: #2b2b2b;
                color: #ffffff;
                border: none;
                padding: 10px;
                border-radius: 5px;
            }
            QPushButton {
                background-color: #5c5c5c;
                border: none;
                color: #ffffff;
                padding: 10px;
                border-radius: 5px;
            }
            QPushButton:hover {
                background-color: #707070;
            }
        """)
        
    def launch_chat(self):
        if self.agreement_checkbox.isChecked():
            self.chat_interface = ChatInterface()
            self.chat_interface.show()
            self.close()
        else:
            self.agreement_checkbox.setStyleSheet("color: red;")
            
class ChatInterface(QWidget):
    def __init__(self):
        super().__init__()
        
        self.initUI()
        
    def initUI(self):
        loadUi('chat_interface.ui', self)
        
        self.send_button.clicked.connect(self.send_message)
        
        # Apply CSS styling
        self.setStyleSheet("""
            QWidget {
                background-color: #2b2b2b;
                color: #ffffff;
                font-family: 'Arial', sans-serif;
            }
            QTextEdit {
                background-color: #3c3c3c;
                border: none;
                color: #ffffff;
                padding: 10px;
                border-radius: 5px;
            }
            QLineEdit {
                background-color: #3c3c3c;
                border: 1px solid #555555;
                color: #ffffff;
                padding: 10px;
                border-radius: 5px;
            }
            QLineEdit::placeholder {
                color: #aaaaaa;
            }
            QPushButton {
                background-color: #5c5c5c;
                border: none;
                color: #ffffff;
                padding: 10px;
                border-radius: 5px;
            }
            QPushButton:hover {
                background-color: #707070;
            }
        """)

        self.user_input.setPlaceholderText("Type text here...")
        self.user_input.setFocus()  # Set focus on the input field
        
    def send_message(self):
        user_input = self.user_input.text()
        self.chat_history.append(f"User: {user_input}")
        
        response = model.send_message(user_input)
        message_to_user = handle_response(response, user_input)
        
        assistant_message = json.loads(message_to_user).get('user_message', '')
        
        self.chat_history.append(f"Assistant: {assistant_message}")
        self.user_input.clear()

if __name__ == '__main__':
    app = QApplication(sys.argv)
    launch_interface = LaunchInterface()
    launch_interface.show()
    sys.exit(app.exec_())