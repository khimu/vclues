package com.vclues.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vclues.core.data.Descriptor;

@Api(value = "/descriptor", description = "Descriptor Management", tags = "descriptor")
@RestController
@RequestMapping("/descriptor/")
public class DescriptorController {
	private final static Logger logger = LoggerFactory.getLogger(DescriptorController.class);
	
	private final static int LIMIT = 100;

	//private DescriptorRepository descriptorRepository;
	
	@ApiOperation(value = "Get all descriptors for Admin", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid data") })
	@RequestMapping(value = "admin/{accountId}", method = RequestMethod.GET)
	public List<Descriptor> admin(
			@ApiParam(value = "Account holder ID")  @PathVariable("accountId") String accountId,
			@ApiParam(value = "Pagination start page")  @RequestParam(value = "start", required = false, defaultValue = "0") int start) {

		/* TODO
		 * check if this is an Admin user
		 */
		//return descriptorRepository.findAll();
		return null;
	}

	@ApiOperation(value = "Add Descriptor to account", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid data") })
	@RequestMapping(value = "{accountId}", method = RequestMethod.POST)
	public void post(
			@ApiParam(value = "Descriptor JSON") @RequestBody Descriptor descriptor,
			@ApiParam(value = "Account holder ID") @PathVariable("accountId") String accountId) {
		//descriptorRepository.save(descriptor);
	}

	@ApiOperation(value = "Get a descriptor by descriptor ID for Account holder", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid data") })
	@RequestMapping(value = "{accountId}/{name}/{contact}", method = RequestMethod.GET)
	public Descriptor getOne(@ApiParam(value = "Descriptor Name") @PathVariable("name") String name,
			@ApiParam(value = "Descriptor Contact") @PathVariable("contact") String contact,
			@ApiParam(value = "Account holder ID") @PathVariable("accountId") String accountId) {
		
		//return descriptorRepository.findByDescriptorKey(accountId + name + contact);
		return null;
	}

	@ApiOperation(value = "Get all descriptors for account holder", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid data") })
	@RequestMapping(value = "all/{accountId}", method = RequestMethod.GET)
	public List<Descriptor> getMultiple(
			@ApiParam(value = "Account holder ID")  @PathVariable("accountId") String accountId,
			@ApiParam(value = "Pagination start page")  @RequestParam(value = "start", required = false, defaultValue = "0") int start) {
		//return descriptorRepository.findByAccountId(accountId);
		return null;
	}

	@ApiOperation(value = "Delete descriptor by descriptor ID for account holder", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid data") })
	@RequestMapping(value = "{accountId}/{descriptorId}", method = RequestMethod.DELETE)
	public void delete(@ApiParam(value = "Account holder ID") @PathVariable("accountId") String accountId,
			@ApiParam(value = "Descriptor ID")  @PathVariable("descriptorId") String descriptorId) {

	}

}
