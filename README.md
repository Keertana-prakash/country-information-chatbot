# 🌍 Country Information Chatbot

A rule-based Country Information Chatbot built using **Java and Spring Boot**. The chatbot provides information about a country's capital, national animal, national flower, and general country details.

The application also uses **session-based conversation context** to remember the country selected by the user during a conversation.

---

## ✨ Features

- 💬 Interactive chatbot web interface
- 🌍 Country information lookup
- 🏛️ Capital information
- 🐾 National animal information
- 🌸 National flower information
- 📋 General country information
- 🧠 Session-based conversation memory
- 🔤 Case-insensitive country lookup
- ⚡ Asynchronous messaging using JavaScript `fetch()`
- 📱 Responsive chatbot interface
- 📄 Country data stored in JSON
- 🚫 No AI, LLM, or external API required

---

## 🛠️ Technologies Used

### Backend
- Java 21
- Spring Boot 4.1.1
- Spring MVC
- Maven
- Jackson
- Apache Tomcat

### Frontend
- HTML5
- CSS3
- JavaScript
- Fetch API
- Thymeleaf

### Data & Session Management
- JSON
- HttpSession
- ConversationContext

### Development Tools
- Visual Studio Code
- Amazon Q Developer
- Git
- GitHub

---

## 🏗️ Project Architecture

```text
                    User
                     │
                     ▼
              index.html
          HTML + CSS + JavaScript
                     │
                     │ fetch()
                     ▼
              POST /chat
                     │
                     ▼
           ChatbotController
                     │
                     ▼
              HttpSession
                     │
                     ▼
          ConversationContext
                     │
                     ▼
           RuleBasedEngine
                     │
                     ▼
             ChatbotService
                     │
                     ▼
           CountryDataLoader
                     │
                     ▼
          countries_data.json
