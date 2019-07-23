package com.cafe24.shoppingmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.repository.ProductDao;
import com.cafe24.shoppingmall.vo.ProductVo;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;
	
	public boolean add(ProductVo productVo) {
		return 1==productDao.insertProduct(productVo);
	}

	public ProductVo getProductByProductNo(Long productNo) {
		
		return productDao.selectProductByProductNo(productNo);
	}

	public boolean modify(ProductVo productVo) {
		
		return 1==productDao.updateProduct(productVo);
	}

	public boolean remove(Long productNo) {
		
		return 1==productDao.deleteProduct(productNo);
	}

}
