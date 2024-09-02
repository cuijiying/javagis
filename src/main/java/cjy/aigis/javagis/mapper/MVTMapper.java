package cjy.aigis.javagis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MVTMapper {
    Object getTile(String layer, int z, int x, int y);
}