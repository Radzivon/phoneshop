package com.es.core.model.color;

import java.util.Objects;

public class Color {
    private Long id;
    private String code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color color = (Color) o;
        return Objects.equals(id, color.id) &&
                Objects.equals(code, color.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }
}