package cjy.aigis.javagis;

import cjy.aigis.javagis.mapper.MVTMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MVTTest {

    @Autowired
    private MVTMapper mvtMapper;

    @Test
    public void getTileTest() {
        mvtMapper.getTile("gis_osm_water_a_free_1",1,1,1);
    }
}
