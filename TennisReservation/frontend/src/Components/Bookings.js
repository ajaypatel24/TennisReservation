import React, { useState } from 'react';
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import './styles.css'


export default function Bookings() {
  const [value, onChange] = useState(new Date());

  

  return (
    <div>
        <div className="wrapper">
      <Calendar
        onChange={onChange}
        value={value}
      />
      </div>
      
    </div>
  );
}







