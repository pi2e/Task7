package com.cfs.dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import com.cfs.databean.Position;


	public class PositionDAO extends GenericDAO<Position>{

		public PositionDAO(ConnectionPool cp, String tableName) throws DAOException {
			
			super(Position.class, tableName, cp);
		}

	}

