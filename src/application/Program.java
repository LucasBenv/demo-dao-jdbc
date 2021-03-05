package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		
		SellerDao sellerdao	= DaoFactory.createSellerDao();	
		
		Seller seller = sellerdao.findById(3);
		
		System.out.println("FindById : ");
		System.out.println(seller);
		
		System.out.println("FindByDepartment: ");
		Department dep = new Department(2, null);
		List<Seller> list = sellerdao.findByDepartment(dep);
		for(Seller obj: list) {
			System.out.println(obj);
		}
		
		System.out.println("FindAll: ");
		List<Seller> All = sellerdao.findaAll();
		for(Seller obj: All	) {
			System.out.println(obj);
		}
		
		System.out.println("Insert: ");
		Seller newSeller = new Seller(0 , "Greg", "Greg@gmail.com", new Date(), 4000.0, dep);
		sellerdao.insert(newSeller);
		System.out.println("Insert ID: " + newSeller.getId());
		
		System.out.println("Update: ");
		seller = sellerdao.findById(10);
		seller.setName("Lucas");
		seller.setEmail("Lucas@gmail.com");
		sellerdao.update(seller);
		System.out.println("Update: ");


	}

}
