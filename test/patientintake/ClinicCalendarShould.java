package patientintake;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarShould {

   private ClinicCalendar calendar;

   //@BeforeAll -> will run once before all test methods in the class
   @BeforeEach // will run once before each @Test method
   void init() {
      calendar = new ClinicCalendar(LocalDate.of(2018, 8, 26));
   }

   @Test
   void allowEntryOfAnAppointment() {
      calendar.addAppointment("Jim", "Weaver", "avery",
         "09/01/2018 2:00 pm");
      List<PatientAppointment> appointments = calendar.getAppointments();
      assertNotNull(appointments);
      assertEquals(1, appointments.size());
      PatientAppointment enteredAppt = appointments.get(0);

//      assertEquals("Jim", enteredAppt.getPatientFirstName());
//      assertEquals("Weaver", enteredAppt.getPatientLastName());
//      assertSame(Doctor.avery, enteredAppt.getDoctor()); //exactly the same object in memory
//      assertEquals("9/1/2018 02:00 PM",
//         enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a", Locale.US)));
//      
      assertAll(
         () -> assertEquals("Jim", enteredAppt.getPatientFirstName()),
         () -> assertEquals("Weaver", enteredAppt.getPatientLastName()),
         () -> assertSame(Doctor.avery, enteredAppt.getDoctor()),
         () -> assertEquals("9/1/2018 02:00 PM",
            enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a", Locale.US)))
      );
   }

   @Test
   void returnTrueForHasAppointmentsIfThereAreAppointments() {
      calendar.addAppointment("Jim", "Weaver", "avery",
         "09/01/2018 2:00 pm");
      assertTrue(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
   }

   @Test
   void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
      assertFalse(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
   }

   @Test
   void returnCurrentDaysAppointments() {
      calendar.addAppointment("Jim", "Weaver", "avery",
         "08/26/2018 2:00 pm");
      calendar.addAppointment("Jim", "Weaver", "avery",
         "08/26/2018 3:00 pm");
      calendar.addAppointment("Jim", "Weaver", "avery",
         "09/01/2018 2:00 pm");
      assertEquals(2, calendar.getTodayAppointments().size());
   }
}