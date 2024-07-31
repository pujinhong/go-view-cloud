package com.ruoyi.view.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.view.config.V2Config;
import com.ruoyi.view.entity.GoviewProject;
import com.ruoyi.view.entity.GoviewProjectData;
import com.ruoyi.view.entity.SysFile;
import com.ruoyi.view.entity.vo.*;
import com.ruoyi.view.service.IGoviewProjectDataService;
import com.ruoyi.view.service.IGoviewProjectService;
import com.ruoyi.view.service.ISysFileService;
import com.ruoyi.view.util.DateUtil;
import com.ruoyi.view.util.StringUtil;
import com.ruoyi.view.util.UuidUtil;
import com.ruoyi.view.util.ConvertUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fc
 * @since 2023-04-30
 */
@RestController
@RequestMapping("/api/goview/project")
public class GoviewProjectController{
	@Autowired
	private ISysFileService iSysFileService;
	@Autowired
	private V2Config v2Config;
	@Autowired
	private IGoviewProjectService iGoviewProjectService;
	@Autowired
	private IGoviewProjectDataService iGoviewProjectDataService;
	

	@GetMapping("/list")
	@ResponseBody
	public ResultTable list(Tablepar tablepar){
		Page<GoviewProject> page= new Page<GoviewProject>(tablepar.getPage(), tablepar.getLimit());
		IPage<GoviewProject> iPages=iGoviewProjectService.page(page, new LambdaQueryWrapper<GoviewProject>());
		ResultTable resultTable=new ResultTable();
		resultTable.setData(iPages.getRecords());
		resultTable.setCode(200);
		resultTable.setCount(iPages.getTotal());
		resultTable.setMsg("获取成功");
		return resultTable;
	}
	
	
	/**
     * 新增保存
     * @param 
     * @return
     */
	//@Log(title = "项目表新增", action = "111")
	//(value = "新增", notes = "新增")
	@PostMapping("/create")
	@ResponseBody
	public AjaxResult add(@RequestBody GoviewProject goviewProject){
		goviewProject.setCreateTime(DateUtil.now());
		goviewProject.setState(-1);
		boolean b=iGoviewProjectService.save(goviewProject);
		if(b){
			return AjaxResult.ok("创建成功");
		}else{
			return AjaxResult.error("创建失败");
		}
	}
	
	
	/**
	 * 项目表删除
	 * @param ids
	 * @return
	 */
	//@Log(title = "项目表删除", action = "111")
	//(value = "删除", notes = "删除")
	@DeleteMapping("/delete")
	@ResponseBody
	public AjaxResult remove(String ids){
		List<String> lista= ConvertUtil.toListStrArray(ids);
		Boolean b=iGoviewProjectService.removeByIds(lista);
		if(b){
			return AjaxResult.ok();
		}else{
			return AjaxResult.error();
		}
	}
	
	//(value = "修改保存", notes = "修改保存")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody GoviewProject goviewProject)
    {
		Boolean b= iGoviewProjectService.updateById(goviewProject);
		if(b){
			return AjaxResult.ok();
		}else{
			return AjaxResult.error();
		}
    }
	
	
	//(value = "项目重命名", notes = "项目重命名")
    @PostMapping("/rename")
    @ResponseBody
    public AjaxResult rename(@RequestBody GoviewProject goviewProject)
    {
		
		LambdaUpdateWrapper<GoviewProject> updateWrapper=new LambdaUpdateWrapper<GoviewProject>();
		updateWrapper.eq(GoviewProject::getId, goviewProject.getId());
		updateWrapper.set(GoviewProject::getProjectName, goviewProject.getProjectName());
		Boolean b=iGoviewProjectService.update(updateWrapper);
		if(b){
			return AjaxResult.ok();
		}else{
			return AjaxResult.error();
		}
    }
	
	
	//发布/取消项目状态
    @PutMapping("/publish")
	@ResponseBody
    public AjaxResult updateVisible(@RequestBody GoviewProject goviewProject){
    	if(goviewProject.getState()==-1||goviewProject.getState()==1) {
    	
    		LambdaUpdateWrapper<GoviewProject> updateWrapper=new LambdaUpdateWrapper<GoviewProject>();
    		updateWrapper.eq(GoviewProject::getId, goviewProject.getId());
    		updateWrapper.set(GoviewProject::getState, goviewProject.getState());
    		Boolean b=iGoviewProjectService.update(updateWrapper);
			if(b){
				return AjaxResult.ok();
			}else{
				return AjaxResult.error();
			}
    	}
		return AjaxResult.error("警告非法字段");
	}
	
    
    //(value = "获取项目存储数据", notes = "获取项目存储数据")
	@GetMapping("/getData")
	@ResponseBody
    public AjaxResult getData(String projectId, ModelMap map)
    {
		GoviewProject goviewProject= iGoviewProjectService.getById(projectId);
		GoviewProjectData blogText=iGoviewProjectDataService.getProjectid(projectId);
		if(blogText!=null) {
			GoviewProjectVo goviewProjectVo=new GoviewProjectVo();
			BeanUtils.copyProperties(goviewProject,goviewProjectVo);
			goviewProjectVo.setContent(blogText.getContent());
			return AjaxResult.ok("获取成功").setData(goviewProjectVo);
		}
		return AjaxResult.ok("无数据");
        
    }
	
	
	
	//(value = "保存项目数据", notes = "保存项目数据")
	@PostMapping("/save/data")
	@ResponseBody
	public AjaxResult saveData(GoviewProjectData data) {
		
		GoviewProject goviewProject= iGoviewProjectService.getById(data.getProjectId());
		if(goviewProject==null) {
			return AjaxResult.error("没有该项目ID");
		}
		GoviewProjectData goviewProjectData= iGoviewProjectDataService.getOne(new LambdaQueryWrapper<GoviewProjectData>().eq(GoviewProjectData::getProjectId, goviewProject.getId()));
		if(goviewProjectData!=null) {
			 data.setId(goviewProjectData.getId());
			 iGoviewProjectDataService.updateById(data);
			 return AjaxResult.error("数据保存成功");
		}else {
			iGoviewProjectDataService.save(data);
			return AjaxResult.error("数据保存成功");
		}
	}
	
	/**
	 * 上传文件
	 * @param object 文件流对象
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/upload")
	public AjaxResult upload(@RequestBody MultipartFile object) throws IOException{
		String fileName = object.getOriginalFilename();
		//默认文件格式
		String suffixName=v2Config.getDefaultFormat();
		String mediaKey="";
		Long filesize= object.getSize();
		//文件名字
		String fileSuffixName="";
		if(fileName.lastIndexOf(".")!=-1) {//有后缀
			 suffixName = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
			 mediaKey= UuidUtil.snow();
			 fileSuffixName=mediaKey+suffixName;
		}else {//无后缀
			//取得唯一id
			mediaKey= UuidUtil.snow();
		}

		String virtualKey= v2Config.getHttpurl();
		//文件绝对路径
		String absolutePath= v2Config.getFileurl();
		SysFile sysFile=new SysFile();
		sysFile.setId(UuidUtil.snow());
		sysFile.setFileName(fileSuffixName);
		sysFile.setFileSize(Integer.parseInt(filesize+""));
		sysFile.setFileSuffix(suffixName);
		sysFile.setCreateTime(DateUtil.now());
		String filepath=DateUtil.YYYYMMDDHHMMSS(new Date());
		sysFile.setRelativePath(filepath);
		sysFile.setVirtualKey(virtualKey);
		sysFile.setAbsolutePath(absolutePath.replace("file:",""));
		iSysFileService.saveOrUpdate(sysFile);
		File desc = new File(v2Config.getFileurl() + File.separator + filepath + fileSuffixName);
		object.transferTo(desc);

		SysFileVo sysFileVo = new SysFileVo();
		sysFileVo.setFileName(sysFile.getFileName());
		sysFileVo.setFileSize(sysFile.getFileSize());
		sysFileVo.setRelativePath(sysFile.getRelativePath());
		sysFileVo.setCreateTime(sysFile.getCreateTime());
		sysFileVo.setVirtualKey(sysFile.getVirtualKey());
		sysFileVo.setFileurl(StringUtil.PathsJoin(v2Config.getHttpurl(),sysFile.getVirtualKey(),sysFile.getRelativePath(), sysFile.getFileName()));
		return AjaxResult.ok().setData(sysFileVo);
	}
	

}
