package cjy.aigis.javagis.service.impl;

import cjy.aigis.javagis.mapper.MVTMapper;
import cjy.aigis.javagis.service.MVTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MVTServiceImpl implements MVTService {
    @Autowired
    private MVTMapper mvtMapper;

    @Override
    public Object getTile(String layer, int z, int x, int y) {
        String tableName = getTableName(layer);
        return mvtMapper.getTile(tableName,z, x, y);
    }

    // 图层名称转表名
    private String getTableName(String layer){
        String tableName = "";
        switch (layer){
            case "water_surface":
                tableName = "gis_osm_water_a_free_1";
                break;
            case "water_line":
                tableName = "gis_osm_waterways_free_1";
                break;
            default:
                return layer;
        }
        return tableName.toUpperCase();
    }
}
