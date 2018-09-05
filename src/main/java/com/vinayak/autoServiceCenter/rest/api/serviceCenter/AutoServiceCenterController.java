package com.vinayak.autoServiceCenter.rest.api.serviceCenter;

import java.util.List;
import java.util.Optional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.vinayak.autoServiceCenter.entities.ServiceCenter;
import com.vinayak.autoServiceCenter.repositories.ServiceCenterRepository;
import org.slf4j.Logger;


/**
 * 
 * @author vinayak
 *<p>Provides basic operations on service centers.</p>
 */
@RestController
public class AutoServiceCenterController {

	
	@Autowired
	private ServiceCenterRepository serviceCenterRepositories;
	
    Logger logger=LoggerFactory.getLogger(AutoServiceCenterController.class);
    
    
	/**
	 * <p>list all service centers.</p>
	 * @return
	 */
	@RequestMapping(value = RestConstants.ENDPOINT_SEPARATOR, method = RequestMethod.GET, produces = RestConstants.APPLICATION_PRODUCER_TYPE)	public ResponseEntity<List<ServiceCenter>> retrieveAllServiceCenter() {
		logger.info("Request received to list all service centers.");
		List<ServiceCenter> listServiceCenter= serviceCenterRepositories.findAll();
		if(CollectionUtils.isEmpty(listServiceCenter)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ServiceCenter>>(listServiceCenter, HttpStatus.OK);
		
	}
	
	/**
	 * <p>list specific service centers.</p>
	 * @return
	 */
	@RequestMapping(value = RestConstants.BY_ID, method = RequestMethod.GET, produces = RestConstants.APPLICATION_PRODUCER_TYPE)	
 	public ResponseEntity<Optional<ServiceCenter>> retrieveServiceCenter(@PathVariable long id) {
		logger.info("Request received to retrive {0} service center details",id);
		Optional<ServiceCenter> serviceCenter= serviceCenterRepositories.findById(id);
		if(!serviceCenter.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}		
		return new ResponseEntity<Optional<ServiceCenter>>(serviceCenter, HttpStatus.OK);

	}
 /**
  * <p>Delete service center</p>
  * @return
  */
	@RequestMapping(value = RestConstants.BY_ID, method = RequestMethod.DELETE, produces = RestConstants.APPLICATION_PRODUCER_TYPE)	
	public ResponseEntity<ServiceCenter> deleteStudent(@PathVariable long id) {
		logger.info("Request received to delete {0} service center",id);
		serviceCenterRepositories.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	/**
	 * <p>Create service center.</p>
	 * @param serviceCenter
	 * @return
	 */
	@RequestMapping(value = RestConstants.ENDPOINT_SEPARATOR, method = RequestMethod.POST, produces = RestConstants.APPLICATION_PRODUCER_TYPE)
	public ResponseEntity<ServiceCenter> createBook(@RequestBody final ServiceCenter serviceCenter) {
		logger.info("Request received to create ServiceCenter.");
		final ServiceCenter savedServiceCenter = serviceCenterRepositories.save(serviceCenter);
		if (savedServiceCenter != null) {
			return new ResponseEntity<ServiceCenter>(savedServiceCenter, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
}
	
}
