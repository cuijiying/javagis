package cjy.aigis.javagis.controller;

import cjy.aigis.javagis.service.MVTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/tiles")
public class MVTController {
    @Autowired
    private MVTService mvtService;

    @GetMapping("/{layer}/{z}/{x}/{y}.pbf")
    public ResponseEntity<Object> getTile(@PathVariable String layer,@PathVariable int z, @PathVariable int x, @PathVariable int y) {
        Object tile = mvtService.getTile(layer,z, x, y);
        if (tile == null) {
            log.warn("Empty or invalid tile data returned for tile: layer={}, z={}, x={}, y={}", layer, z, x, y);
        } else {
            log.info("参数: z={}, x={}, y={}, 数据长度: data = {}", z, x, y, ((byte[])tile).length);
        }
        // 压缩数据 数据基本上可以缩小一倍
        byte[] compressedTile = null;
        try {
            compressedTile = compressData((byte[])tile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity
                .ok()
                .header("Content-Type", "application/x-protobuf")
                .header("Content-Encoding", "gzip")
                .body(compressedTile);
    }


    private byte[] compressData(byte[] data) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (GZIPOutputStream gzos = new GZIPOutputStream(baos)) {
            gzos.write(data);
        }
        return baos.toByteArray();
    }

}
