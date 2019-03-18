package com.manager.diy.ms.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manager.diy.ms.dao.ModuleDao;
import com.manager.diy.ms.po.Tmodule;

@RestController
@RequestMapping("/module")
public class ModuleController {
	
	@Autowired
	private ModuleDao dao;

	@RequestMapping(value = "/queryModule")
	public List<Tmodule> queryModule(){
		List<Tmodule> list = dao.search();
		return list;
	}

	@RequestMapping(value = "/getModuleById")
	public Tmodule getModuleById(){
		List<Tmodule> list = dao.search();
		return list.get(0);
	}
}
