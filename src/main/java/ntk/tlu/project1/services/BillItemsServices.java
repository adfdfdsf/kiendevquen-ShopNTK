package ntk.tlu.project1.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ntk.tlu.project1.entity.BillitemsEntity;
import ntk.tlu.project1.model.BillitemsModel;
import ntk.tlu.project1.repository.BillItemsRepo;
import ntk.tlu.project1.repository.BillRepo;
import ntk.tlu.project1.repository.ProductRepo;

@Service
public class BillItemsServices {
	@Autowired
	BillRepo billRepo;
	@Autowired
	BillItemsRepo billItemsRepo;
	@Autowired
	BillServices billServices;
	@Autowired
	ProductRepo productRepo;
	@Autowired
	ModelMapper modelMapper;
	public void createBillItems(BillitemsModel billitemsModel) {
		BillitemsEntity billitemsEntity = modelMapper.map(billitemsModel, BillitemsEntity.class);
		billItemsRepo.save(billitemsEntity);
	}
	
	
	
}
