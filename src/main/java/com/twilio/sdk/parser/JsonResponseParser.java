package com.twilio.sdk.parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.sdk.TwilioRestResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class JsonResponseParser.
 */
public class JsonResponseParser implements ResponseParser {
	@Override
    public Map<String, Object> parse(final TwilioRestResponse response) {
		return this.parseJson(response.getResponseText());
	}

	/**
	 * Parses the json.
	 *
	 * @param jsonString the json string
	 * @return the map
	 */
	@SuppressWarnings("unchecked")
	protected Map<String, Object> parseJson(final String jsonString) {
		Map<String, Object> ret = new HashMap<String, Object>();

		try {
			ret = new ObjectMapper().readValue(jsonString, HashMap.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ret;
	}

	/* (non-Javadoc)
	 * @see com.twilio.sdk.parser.ResponseParser#getPagingPropertyKey(com.twilio.sdk.parser.ResponseParser.PagingProperty)
	 */
	@Override
    public String getPagingPropertyKey(final PagingProperty prop) {
		switch (prop) {
		case NEXT_PAGE_URI_KEY:
			return "next_page_uri";
		case START_KEY:
			return "start";
		case END_KEY:
			return "end";
		case PAGE_KEY:
			return "page";
		case NUM_PAGES_KEY:
			return "num_pages";
		case TOTAL_KEY:
			return "total";
		}

		return null;
	}
}
