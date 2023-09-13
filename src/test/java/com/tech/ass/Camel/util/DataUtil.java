package com.tech.ass.Camel.util;

import com.tech.ass.Camel.controller.model.EventRequest;

import java.util.ArrayList;
import java.util.List;

public class DataUtil {
    public static EventRequest getEvent(){
        EventRequest request = new EventRequest();
        request.setEventType("InfoEvent");
        request.setEventBody("A week has seven days");
        return request;
    }

    public static List<EventRequest> getEvents(){
        List<EventRequest> requests = new ArrayList<>();
        EventRequest info1 = new EventRequest();
        info1.setEventType("InfoEvent");
        info1.setEventBody("A week has seven days");
        requests.add(info1);

        EventRequest info2 = new EventRequest();
        info2.setEventType("InfoEvent");
        info2.setEventBody("A year has 52 weeks");
        requests.add(info2);

        EventRequest info3 = new EventRequest();
        info3.setEventType("InfoEvent");
        info3.setEventBody("A day has 24 hours");
        requests.add(info3);

        EventRequest funFact1 = new EventRequest();
        funFact1.setEventType("FunFactEvent");
        funFact1.setEventBody("The scientific term for brain freeze is 'sphenopalatine ganglioneuralgia'.");
        requests.add(funFact1);

        EventRequest funFact2 = new EventRequest();
        funFact2.setEventType("FunFactEvent");
        funFact2.setEventBody("The only letter that doesn’t appear on the periodic table is J.");
        requests.add(funFact2);

        EventRequest funFact3 = new EventRequest();
        funFact3.setEventType("FunFactEvent");
        funFact3.setEventBody("If a Polar Bear and a Grizzly Bear mate, their offspring is called a 'Pizzy Bear'.");
        requests.add(funFact3);
        return requests;
    }
}
