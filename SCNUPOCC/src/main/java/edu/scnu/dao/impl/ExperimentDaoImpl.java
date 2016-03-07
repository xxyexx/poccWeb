package main.java.edu.scnu.dao.impl;

import org.springframework.stereotype.Repository;

import main.java.edu.scnu.dao.ExperimentDao;
import main.java.edu.scnu.entity.Experiment;

@Repository(value="experimentDao")
public class ExperimentDaoImpl extends BaseDaoImpl<Experiment>
	implements ExperimentDao
{

}
