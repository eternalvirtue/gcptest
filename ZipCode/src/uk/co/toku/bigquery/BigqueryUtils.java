package uk.co.toku.bigquery;

import java.io.IOException;
import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.bigquery.Bigquery;
import com.google.api.services.bigquery.BigqueryScopes;
import com.google.api.services.bigquery.model.GetQueryResultsResponse;
import com.google.api.services.bigquery.model.QueryRequest;
import com.google.api.services.bigquery.model.QueryResponse;
import com.google.api.services.bigquery.model.TableRow;

public class BigqueryUtils {
	// FIXME:Google Cloud Platformで作成したプロジェクトIDをセットしてください
	private static String PROJECT_ID = "xxxxxxx";
	/**
	 * Creates an authorized Bigquery client service using Application Default
	 * Credentials.
	 *
	 * @return an authorized Bigquery client
	 * @throws IOException
	 *             if there's an error getting the default credentials.
	 */
	public static Bigquery createAuthorizedClient() throws IOException {
		// Create the credential
		HttpTransport transport = new NetHttpTransport();
		JsonFactory jsonFactory = new JacksonFactory();
		GoogleCredential credential = GoogleCredential.getApplicationDefault(transport, jsonFactory);

		// Depending on the environment that provides the default credentials
		// (e.g. Compute Engine, App
		// Engine), the credentials may require us to specify the scopes we need
		// explicitly.
		// Check for this case, and inject the Bigquery scope if required.
		if (credential.createScopedRequired()) {
			credential = credential.createScoped(BigqueryScopes.all());
		}

		return new Bigquery.Builder(transport, jsonFactory, credential).setApplicationName("Bigquery Samples").build();
	}
	// [END build_service]

	// [START run_query]
	/**
	 * Executes the given query synchronously.
	 *
	 * @param querySql
	 *            the query to execute.
	 * @param bigquery
	 *            the Bigquery service object.
	 * @param projectId
	 *            the id of the project under which to run the query.
	 * @return a list of the results of the query.
	 * @throws IOException
	 *             if there's an error communicating with the API.
	 */
	public static List<TableRow> executeQuery(String querySql, String projectId) throws IOException {
		Bigquery bigquery = createAuthorizedClient();

		QueryResponse query = bigquery.jobs().query(projectId, new QueryRequest().setQuery(querySql)).execute();

		// Execute it
		GetQueryResultsResponse queryResult = bigquery.jobs()
				.getQueryResults(query.getJobReference().getProjectId(), query.getJobReference().getJobId()).execute();

		return queryResult.getRows();
	}

	public static List<TableRow> executeAddressQuery(String queryKey) throws IOException {
		String sql = String.format(
				"SELECT JISCODE, ZIPCODE_5, ZIPCODE_7, PREFECTURE_KANA, CITY_KANA, TOWN_KANA, FULL_ADDRESS_KANA,"
				+" PREFECTURE_KANJI, CITY_KANJI, TOWN_KANJI, FULL_ADDRESS_KANJI, OPTION_1, OPTION_2, OPTION_3,"
				+" OPTION_4, OPTION_5, OPTION_6" 
				+ " FROM [ZIP.ZIPDATA_EXTEND] WHERE FULL_ADDRESS_KANJI LIKE '%%%s%%' ORDER BY ZIPCODE_7 LIMIT 100", queryKey);
		List<TableRow> rows = executeQuery(sql, PROJECT_ID);

		return rows;
	}
}
