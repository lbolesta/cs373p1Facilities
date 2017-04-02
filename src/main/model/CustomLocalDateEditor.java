package main.model;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

public class CustomLocalDateEditor extends PropertyEditorSupport {

   public CustomLocalDateEditor() {
   }

   private LocalDate parseText(String text) {
      LocalDate ldt;
      try {
         ldt = LocalDate.parse(text);
      } catch(Exception ee) {
         ldt = null;
      }

      return ldt;
   }

   @Override
   public void setAsText(String text) throws IllegalArgumentException {
      setValue(parseText(text));
   }

   @Override
   public String getAsText() {
      LocalDate value = parseText(String.valueOf(getValue()));
      return (value != null ? value.toString() : "");
   }

}
