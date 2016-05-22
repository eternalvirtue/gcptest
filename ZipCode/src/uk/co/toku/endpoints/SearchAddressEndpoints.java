package uk.co.toku.endpoints;

import java.io.IOException;
import java.util.List;

import javax.inject.Named;

import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.services.bigquery.model.TableCell;
import com.google.api.services.bigquery.model.TableRow;
import com.google.appengine.api.oauth.OAuthRequestException;

import uk.co.toku.bigquery.BigqueryUtils;
import uk.co.toku.results.AddressResults;
import uk.co.toku.results.ZipcodeExtend;

@Api(name = "searchAddress", version = "v1", namespace = @ApiNamespace(ownerDomain = "toku.co.uk", ownerName = "toku.co.uk", packagePath = ""))
public class SearchAddressEndpoints {
	@ApiMethod(name = "getAddress", httpMethod = ApiMethod.HttpMethod.GET)
	public AddressResults getAddress(@Named("searchAddress") String searchAddress, User user) throws OAuthRequestException, IOException {
		AddressResults results = new AddressResults();
		List<TableRow> rows = BigqueryUtils.executeAddressQuery(searchAddress);
	    if (rows != null) {
	    	int i = 0;
	    	ZipcodeExtend zipcodeExtend = null;
	    	Object fieldObj = null;
    		String fieldValue = "";
		    for (TableRow row : rows) {
		    	i = 0;
		    	zipcodeExtend = new ZipcodeExtend();
		    	for (TableCell field : row.getF()) {
		    		fieldObj = field.getV();
		    		fieldValue = "";
		    		if (fieldObj != null && fieldObj instanceof String) {
		    			fieldValue = ((String)fieldObj).trim();
		    		}
		    		switch (i) {
		    		case 0: zipcodeExtend.setJiscode(fieldValue);break;
		    		case 1: zipcodeExtend.setZipcode5(fieldValue);break;
		    		case 2: zipcodeExtend.setZipcode7(fieldValue);break;
		    		case 3: zipcodeExtend.setPrefectureKana(fieldValue);break;
		    		case 4: zipcodeExtend.setCityKana(fieldValue);break;
		    		case 5: zipcodeExtend.setTownKana(fieldValue);break;
		    		case 6: zipcodeExtend.setFullAddressKana(fieldValue);break;
		    		case 7: zipcodeExtend.setPrefectureKanji(fieldValue);break;
		    		case 8: zipcodeExtend.setCityKanji(fieldValue);break;
		    		case 9: zipcodeExtend.setTownKanji(fieldValue);break;
		    		case 10: zipcodeExtend.setFullAddressKanji(fieldValue);break;
		    		case 11: zipcodeExtend.setOption1(fieldValue);break;
		    		case 12: zipcodeExtend.setOption2(fieldValue);break;
		    		case 13: zipcodeExtend.setOption3(fieldValue);break;
		    		case 14: zipcodeExtend.setOption4(fieldValue);break;
		    		case 15: zipcodeExtend.setOption5(fieldValue);break;
		    		case 16: zipcodeExtend.setOption6(fieldValue);break;
		    		default:break;
		    		}
		    		i++;
		    	}
				results.add(zipcodeExtend);
		    }	    	
	    }
		return results;
	}
	
}
