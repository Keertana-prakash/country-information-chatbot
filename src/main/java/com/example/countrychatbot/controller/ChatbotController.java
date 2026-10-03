package com.example.countrychatbot.controller;

import com.example.countrychatbot.engine.RuleBasedEngine;
import com.example.countrychatbot.model.ConversationContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChatbotController {

    private static final String SESSION_KEY = "conversationContext";

    private final RuleBasedEngine ruleBasedEngine;

    public ChatbotController(RuleBasedEngine ruleBasedEngine) {
        this.ruleBasedEngine = ruleBasedEngine;
    }

    @PostMapping("/chat")
    @ResponseBody
    public String chat(@RequestParam("message") String message, HttpSession session) {
        ConversationContext context = (ConversationContext) session.getAttribute(SESSION_KEY);
        if (context == null) {
            context = new ConversationContext();
            session.setAttribute(SESSION_KEY, context);
        }
        return ruleBasedEngine.processMessage(message, context);
    }
}
