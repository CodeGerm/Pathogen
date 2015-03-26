package org.cg.rule.engine.common.persister;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cg.rule.engine.common.osgi.Activator;
import org.cg.rule.engine.common.sample.model.SampleFact;
import org.osgi.framework.BundleContext;

import com.google.common.base.Throwables;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * a file(JSON) persister for Event
 * @author WZ
 *
 */
public class SampleFactFileDao {

	private static final Log LOG = LogFactory.getLog(SampleFactFileDao.class);

	private final String filePath;

	private final Gson gson;

	public SampleFactFileDao (String filePath) {
		gson = new GsonBuilder().create();
		this.filePath = filePath;
	}

	public List<SampleFact> loadAll() {
		try {  		
			BufferedReader br;

			BundleContext bc = Activator.getBundleContext();
			if (bc!=null) {
				InputStream url = bc.getBundle().getEntry(filePath).openStream();
				br =new BufferedReader(new InputStreamReader(url));
			} else {			
				br = new BufferedReader(new FileReader(filePath));   
			}

			//convert the json string back to object  
			SampleFactBatch sampleFacts = gson.fromJson(br, SampleFactBatch.class);  
			return sampleFacts.getAll();
		} catch (Exception e) {  
			LOG.error(Throwables.getStackTraceAsString(e));
			throw new IllegalArgumentException("Invalid input file: " + filePath ); 
		}  
	}

	public void save (SampleFactBatch sampleFacts) {
		String json = gson.toJson(sampleFacts);
		try {
			FileWriter writer = new FileWriter(filePath);
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			LOG.error(Throwables.getStackTraceAsString(e));
			throw new IllegalArgumentException("Invalid output file: " + filePath ); 
		}
	}
}
