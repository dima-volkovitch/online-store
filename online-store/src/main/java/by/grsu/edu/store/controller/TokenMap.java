package by.grsu.edu.store.controller;

import java.util.HashMap;
import java.util.Map;

public class TokenMap {
	private static TokenMap tokenMap;

	private Map<String, Long> map;

	private TokenMap() {
		map = new HashMap<>();
	}

	public static TokenMap getTokenMap() {
		if (tokenMap == null) {
			tokenMap = new TokenMap();
		}
		return tokenMap;
	}

	public Long put(String token, Long id) {
		return map.put(token, id);
	}

	public Long remove(String token) {
		return map.remove(token);
	}

	public boolean containsToken(String token) {
		return map.containsKey(token);
	}

	public Long get(String token) {
		return map.get(token);
	}
}
