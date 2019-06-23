package com.joham.demo.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author joham
 */
@RestController
public class StateMachineController {

    @Autowired
    private StateMachine<States, Events> stateMachine;

    @RequestMapping(value = "testStateMachine")
    public String testStateMachine() {
        stateMachine.start();
        stateMachine.sendEvent(Events.PAY);
        stateMachine.sendEvent(Events.RECEIVE);
        return "success";
    }
}
