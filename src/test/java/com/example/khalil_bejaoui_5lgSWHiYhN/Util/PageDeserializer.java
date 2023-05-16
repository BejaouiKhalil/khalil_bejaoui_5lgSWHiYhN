package com.example.khalil_bejaoui_5lgSWHiYhN.Util;

import com.example.khalil_bejaoui_5lgSWHiYhN.dto.StudentDTO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.data.domain.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PageDeserializer<T> extends StdDeserializer<Page<T>> {

    public PageDeserializer() {
        this(null);
    }

    public PageDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Page<T> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        JsonNode contentNode = node.get("content");
        List<T> content = new ArrayList<>();
        if (contentNode.isArray()) {
            for (JsonNode elementNode : contentNode) {
                T element = (T) jp.getCodec().treeToValue(elementNode, StudentDTO.class);
                content.add(element);
            }
        }

        int totalElements = node.get("totalElements").asInt();
        int totalPages = node.get("totalPages").asInt();

        // Extract other necessary properties for Pageable
        int pageNumber = node.get("number").asInt();
        int pageSize = node.get("size").asInt();

        // Create Pageable instance
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return new PageImpl<>(content, pageable, totalElements);
    }
}
