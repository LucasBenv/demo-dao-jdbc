package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.Db;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		
		PreparedStatement st =  null;
		ResultSet rs = null;
		
		try{
			st = conn.prepareStatement(
					" SELECT seller.*,department.Name as DepName "
					+" FROM seller INNER JOIN department "
					+" ON seller.DepartmentId = department.Id "
					+" WHERE seller.Id = ? "
			);
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				
				Department dp = instantiateDepartment(rs);
		
				Seller obj = instantiateSeller(rs, dp);
					
				
				return obj;
			}
			return null;	
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			Db.closeStatement(st);
			Db.closeResultSet(rs);
		}
		
		
	}

	private Seller instantiateSeller(ResultSet rs, Department dp) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dp);	
		return obj;
	}
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
			Department dp = new Department();
			dp.setId(rs.getInt("DepartmentId"));
			dp.setName(rs.getString("DepName"));
			return dp;
	}
	@Override
	public List<Seller> findaAll() {
		PreparedStatement st =  null;
		ResultSet rs = null;
		
		try{
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId = department.Id "
					+"ORDER BY Name "
			);
			rs = st.executeQuery();
				
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				
				Seller obj = instantiateSeller(rs, dep);		
				list.add(obj);
				
			}
			return list;	
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			Db.closeStatement(st);
			Db.closeResultSet(rs);
		}
	}
	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st =  null;
		ResultSet rs = null;
		
		try{
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId = department.Id "
					+"WHERE DepartmentId = ? "
					+"ORDER BY Name "
			);
			
			st.setInt(1, department.getId());
			rs = st.executeQuery();
				
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				
				Seller obj = instantiateSeller(rs, dep);		
				list.add(obj);
				
			}
			return list;	
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			Db.closeStatement(st);
			Db.closeResultSet(rs);
		}
	}

}
