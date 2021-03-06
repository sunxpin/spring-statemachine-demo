/*
package com.sun.config;

import com.sun.events.Events;
import com.sun.states.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

@Configuration
@EnableStateMachine
public class StateMachineConfig2
        extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config)
            throws Exception {
        config
             .withConfiguration()
                .autoStartup(true)
                    .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
        states
             .withStates()
                .initial(States.S1)
                .state(States.S2)
                .and()
             .withStates()
                .parent(States.S2)
                .initial(States.S2I)
                .state(States.S21)
                .end(States.S2F)
                .and()
             .withStates()
                .parent(States.S2)
                .initial(States.S3I)
                .state(States.S31)
                .end(States.S3F);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
                .withExternal()
                    .source(States.S1).target(States.S2).event(Events.E1)
                    .and()
                .withExternal()
                    .source(States.S2I).target(States.S1).event(Events.E2);
    }

    @Bean
    public StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<States, Events>() {
            @Override
            public void stateChanged(State<States, Events> from, State<States, Events> to) {
                System.out.println("State change to " + to.getId());
            }
        };
    }
}*/
