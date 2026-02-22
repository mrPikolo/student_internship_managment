package faculty_app.util;

import java.lang.reflect.Type;
import java.time.LocalDate;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

	@Override
	public JsonElement serialize(LocalDate date, Type type, JsonSerializationContext context) {
		return new JsonPrimitive(date.toString());
	}

	@Override
	public LocalDate deserialize(JsonElement element, Type type, JsonDeserializationContext context) {
		return LocalDate.parse(element.getAsString());
	}
}
