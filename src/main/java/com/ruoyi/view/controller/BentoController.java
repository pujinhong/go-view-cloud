package com.ruoyi.view.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.view.entity.BentoSqlEntity;
import com.ruoyi.view.entity.vo.AjaxResult;
import com.ruoyi.view.entity.vo.BentoSqlVo;
import com.ruoyi.view.service.IBentoService;
import com.ruoyi.view.util.db.DynamicJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author pjh
 * @created 2024/7/2
 */

@RestController
@RequestMapping("/bento")
public class BentoController {

    @CrossOrigin
    @GetMapping("/sql/{name}")
    public AjaxResult oneByName(@PathVariable("name") String name, @RequestParam Map<String, Object> params) throws IOException {
        BentoSqlEntity one = bentoService.getOne(new LambdaQueryWrapper<BentoSqlEntity>().eq(BentoSqlEntity::getName, name));
        if(one == null){
            return AjaxResult.error("未找到对应的块");
        }

        List<Map<String, Object>> data = dynamicJDBC.findListByMap(one.getDbCode(), one.getStatement(), params);
        BentoSqlVo vo = new BentoSqlVo();
        vo.setName(one.getName());
        vo.setData(data);
        vo.setTitle(one.getTitle());
        return AjaxResult.ok().setData(vo);
    }
    @CrossOrigin
    @GetMapping("/json/{name}")
    public AjaxResult jsonByName(@PathVariable("name") String name, @RequestParam Map<String, Object> params) throws IOException {
        BentoSqlEntity one = bentoService.getOne(new LambdaQueryWrapper<BentoSqlEntity>().eq(BentoSqlEntity::getName, name));
        if(one == null){
            return AjaxResult.error("未找到对应的块");
        }

        BentoSqlVo vo = new BentoSqlVo();
        vo.setName(one.getName());
        vo.setData(new BentoSqlEntity());
        vo.setTitle(one.getTitle());
        return AjaxResult.ok().setData(vo);
    }

    @Autowired
    private IBentoService bentoService;

    @Autowired DynamicJDBC dynamicJDBC;

    @GetMapping("/list")
    public AjaxResult list(){
        return AjaxResult.ok().setData(bentoService.list());
    }
    @GetMapping("/{id}")
    public AjaxResult oneById(@PathVariable("id") Integer id){
        BentoSqlEntity one = bentoService.getOne(new LambdaQueryWrapper<BentoSqlEntity>().eq(BentoSqlEntity::getId, id));
        return AjaxResult.ok().setData(one);
    }

    @PostMapping
    public AjaxResult insert(@RequestBody BentoSqlEntity entity ){
        boolean save = bentoService.save(entity);
        return AjaxResult.ok().setData(save);
    }

    @DeleteMapping("/{id}")
    public AjaxResult deleteOne(@PathVariable("id") Integer id){
        boolean remove = bentoService.removeById(id);
        return AjaxResult.ok().setData(remove);
    }

    @PutMapping
    public AjaxResult update(@RequestBody BentoSqlEntity entity){
        boolean updateById = bentoService.updateById(entity);
        return AjaxResult.ok().setData(updateById);
    }





}
