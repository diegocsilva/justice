import React from 'react';
import { DatePickerStyled } from './styled';

function DatePicker(props) {
  return (
    <DatePickerStyled
      id="date"
      label={props.label}
      type="date"
      defaultValue={props.defaultValue}
      InputLabelProps={{
        shrink: true,
      }}
    />
  );
}

export default DatePicker;
