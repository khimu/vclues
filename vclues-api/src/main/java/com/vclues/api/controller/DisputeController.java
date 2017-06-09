package com.vclues.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vclues.core.data.Dispute;
import com.vclues.core.mongo.repository.GameRepository;

/**
 * Case dispute controller
 * 
 * @author khimung
 *
 */
@Api(value = "/disputes", description = "Disputes Management", tags = "disputes")
@RestController
@RequestMapping("/disputes/")
public class DisputeController {
	private final static Logger logger = LoggerFactory.getLogger(DisputeController.class);
	
	@Autowired
	//private GameRepository disputeRepository;
	
	private final static int LIMIT = 100;
	
	@ApiOperation(value = "Get all disputes for Admin", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid data") })
	@RequestMapping(value = "admin/{accountId}", method = RequestMethod.GET)
	public List<Dispute> admin(
			@ApiParam(value = "Account holder ID")  @PathVariable("accountId") String accountId,
			@ApiParam(value = "Pagination start page")  @RequestParam(value = "start", required = false, defaultValue = "0") int start) {

		/* TODO
		 * check if this is an Admin user
		 */
		//return disputeRepository.findAll();
		return null;
	}
	
	@ApiOperation(value = "Add Dispute to account", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid data") })
	@RequestMapping(value = "{accountId}", method = RequestMethod.POST)
	public void post(@ApiParam(value = "Dispute JSON")@RequestBody Dispute dispute, @ApiParam(value = "Account holder ID") @PathVariable("accountId") String accountId) {
		//disputeRepository.save(dispute);
	}
	
	@RequestMapping(value = "{accountId}/{disputeId}", method = RequestMethod.GET)
	public @ResponseBody Dispute getOne(@PathVariable("disputeId") String disputeId, @PathVariable("accountId") String accountId) {
		//return disputeRepository.findOne(disputeId);
		return null;
	}
	
	@RequestMapping(value = "all/{accountId}/{disputeId}", method = RequestMethod.GET)
	public void getMultiple(@PathVariable("accountId") String accountId, @PathVariable("disputeId") String disputeId, @RequestParam(value = "start", required = false, defaultValue = "0") int start) {

	}

	@RequestMapping(value = "{accountId}/{disputeId}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("accountId") String accountId, @PathVariable("disputeId") String disputeId) {
		
	}

}
