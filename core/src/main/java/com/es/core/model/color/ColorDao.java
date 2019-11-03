package com.es.core.model.color;

import java.util.List;

public interface ColorDao  {
    List<Color> getColorsForPhoneById(Long key);
}
