package usi.justmove.gathering.surveys.strategies.schedulebased;

import usi.justmove.gathering.base.StateMachineInputProvider;

/**
 * Created by usi on 06/02/17.
 */

public abstract class ScheduleBasedInputProvider implements StateMachineInputProvider<ScheduleBasedSymbol> {

    protected abstract boolean checkSchedule();

    @Override
    public ScheduleBasedSymbol getInput() {
        if(checkSchedule()) {
            return ScheduleBasedSymbol.NOTIFY;
        } else {
            return ScheduleBasedSymbol.WAIT;
        }
    }
}
