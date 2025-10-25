package com.example.agent;

import com.google.adk.agents.LlmAgent;
import com.google.adk.web.AdkWebServer;

/**
 * Here the idea is delegate the work to some subagents. Each of them cover a specific functionality
 * We have:
 *     1) Order Agent, which help customers to know about their package traceability
 *     2) After Sale Agent, which offers solutions to customers, related to their received products.
 *     3) Support Agent: is in charge of make any kind of help to customers related to their purchases
 */
public class SupportAgent {
    public static void main(String[] args) {
        LlmAgent topicSearchAgent = LlmAgent.builder()
                .name("order-agent")
                .description("Order agent")
                .instruction("""
                Your role is to help our customers
                with all the questions they may have about their orders.
                Always respond that the order has been received, prepared,
                and is now out for delivery.
                """)
                .model("gemini-2.5-flash")
                .build();

        LlmAgent socialMediaAgent = LlmAgent.builder()
                .name("after-sale-agent")
                .description("After sale agent")
                .instruction("""
                You are an after sale agent,
                helping customers with the product they received.
                When a customer has a problem,
                suggest the person to switch the product off and on again.
                """)
                .model("gemini-2.5-flash")
                .build();

        AdkWebServer.start(LlmAgent.builder()
                .name("support-agent")
                .description("Customer support agent")
                .instruction("""
                Your role is help our customers.
                Call the `order-agent` for all questions related to order status.
                Call the `after-sale-agent` for inquiries about the received product.
                """)
                .model("gemini-2.5-flash")
                .subAgents(socialMediaAgent, topicSearchAgent)
                .build()
        );
    }
}