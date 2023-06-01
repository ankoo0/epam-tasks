package com.epam.rd.autocode.floodfill;

import com.epam.rd.autocode.floodfill.solution.Flooder;
import com.epam.rd.autocode.floodfill.solution.Wrapper;

public class FloodFillImpl implements FloodFill {

    private final Flooder flooder = new Flooder();


    @Override
    public void flood(String map, FloodLogger logger) {
        Wrapper wrapper = new Wrapper(map);
        logger.log(wrapper.state());
        flooder.fill(wrapper.getFloodMap());
        if (flooder.isMapFlooded(wrapper.state())) {
            logger.log(wrapper.state());
            return;
        }
        flood(wrapper.state(), logger);
    }
}
