package com.example.agent;

import java.time.LocalDate;
import com.google.adk.agents.LlmAgent;
import com.google.adk.tools.GoogleSearchTool;
import com.google.adk.web.AdkWebServer;


/**
 * To run this class and the rest of this package use this command:
 * <p>
 * mvn compile exec:java -Dexec.mainClass=com.example.agent.RiverPLateLatestNews
 * <p>
 * and then in localhost:8080 to see if works
 */
public class RiverPlateLatestNews {
    public static void main(String[] args) {
        AdkWebServer.start(LlmAgent.builder()
                .name("river-plate-news-search-agent")
                .description("A River Plate news search agent")
                .instruction("""
                You are a River Plate news search agent.
                Use the `google_search` tool
                when asked to search for recent events and information.
                Today is \
                """ + LocalDate.now())
                .model("gemini-2.5-flash")
                .tools(new GoogleSearchTool())
                .build());
    }
}