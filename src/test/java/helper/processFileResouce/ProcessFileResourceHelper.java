package helper.processFileResouce;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class ProcessFileResourceHelper {

	private JSONObject jsonObject;

	public ProcessFileResourceHelper(String filename) throws IOException, JSONException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(filename).getFile());
		String content = FileUtils.readFileToString(file, "UTF8");
		jsonObject = new JSONObject(content);
	}

	public JSONObject getJsonObject() {
		return jsonObject;

	}

	public JSONObject getNamedJSONObject(String name) throws JSONException {
		JSONObject testdata = jsonObject.getJSONObject("testdata");
		return testdata.getJSONObject(name);
	}

	public JSONObject changeAttribute(JSONObject original, String key, String value) throws JSONException {
		return original.put(key, value);
	}

}
