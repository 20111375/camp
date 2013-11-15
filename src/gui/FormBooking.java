package gui;

import camp.Pitch;
import camp.PitchList;
import com.jcalendar.event.CalendarEvent;
import com.jcalendar.pane.calendar.CalendarPane;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Andrew on 07/11/13.
 */
public class FormBooking extends JDialog {
    private JComboBox PitchType;
    private JList SearchResultList;
    private JButton SearchButton;
    private JButton FindCustomerButton;
    private JButton NewCustomerButton;
    private JButton ConfirmButton;
    private JButton extendABookingButton;
    private JTextArea CustomerDetails;
    private JTextArea PitchDetails;
    private JComboBox EndDate;
    private CalendarPane FromDate;
    private JPanel ReservationPane;
    private JPanel ExtendBooking;
    private JPanel FormBooking;
    private JPanel BookForm;
    private JButton buttonCancel;
    private JButton buttonOK;
    private String TypeName;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private String StartDate = null;
    private int FinishDate;
    private String PitchSelected;
    private List<Pitch> pitches = null;
    private int bookingClient;
    private DialogSearch search = new DialogSearch();

    public DialogSearch getSearch() {
        return search;
    }

    public String getPitchSelected() {
        return PitchSelected;
    }

    public void setPitchSelected(String pitchSelected) {
        PitchSelected = pitchSelected;
    }

    public String makeFinishDate(int day, String Start) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.parse(Start));
            calendar.add(Calendar.DATE, day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFormat.format(calendar.getTime());
    }

    public int getFinishDate() {
        return FinishDate;
    }

    public void setFinishDate(int finishDate) {
        FinishDate = finishDate;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public JTextArea getPitchDetails() {
        return PitchDetails;
    }

    public void setPitchDetails(JTextArea pitchDetails) {
        PitchDetails = pitchDetails;
    }

    public FormBooking() {
        setContentPane(FormBooking);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        FormBooking.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        extendABookingButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        ConfirmButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        NewCustomerButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
                new DialogCustomerForm().make();
            }
        });
        FindCustomerButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
                getSearch().make();
            }
        });
        SearchButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StartDate != null && FinishDate != 0 && TypeName != null) {
                    String tempTime = makeFinishDate(getFinishDate(), getStartDate());
                    try {
                        pitches = new PitchList().Items(getStartDate(), tempTime.toString(), getTypeName());
                    } catch (Exception e1) {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    SearchResultList.setListData(pitches.toArray());
                }
                // query db with start date, end date and pitch type
            }
        });
        PitchType.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                setTypeName((String) PitchType.getSelectedItem());
            }
        });
        FromDate.addCalendarSelectionListener(new CalListen() {
            public void selectionChanged(CalendarEvent arg0) {
                try {
                    setStartDate(dateFormat.format(arg0.getDate()));
                    //System.out.println(getStartDate());
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        EndDate.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                setFinishDate(EndDate.getSelectedIndex());
                //System.out.println(getFinishDate());
            }
        });

        SearchResultList.addListSelectionListener(new ListSelectionListener() {
            /**
             * Called whenever the value of the selection changes.
             *
             * @param e the event that characterizes the change.
             */
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
                if (e.getValueIsAdjusting() == true) {
                    if (isEmpty()) {
                        PitchDetails.setText(null);
                    }
                    setPitchSelected(SearchResultList.getSelectedValue().toString());
                    PitchDetails.append("Pitch name: " + pitches.get(SearchResultList.getSelectedIndex()).getPitchName() + "\n");
                    PitchDetails.append("Pitch type: ");
                    String[] array = pitches.get(SearchResultList.getSelectedIndex()).getTypeName();
                    for (String E : array) {
                        if (E != null) {
                            PitchDetails.append(E + ", ");
                            System.out.println(E + "\n");
                        }
                    }
                    PitchDetails.append("\n");
                    PitchDetails.append("Start date: " + getStartDate() + "\n");
                    PitchDetails.append("Finish date: " + makeFinishDate(getFinishDate(), getStartDate()).toString() + "\n");
                }

            }
        });
    }

    private void onOK() {
// add your code here
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    private boolean isEmpty() {
        String tmp = PitchDetails.getText().trim();
        System.out.println("this is tmp: " + tmp);
        if ((tmp != null) && (tmp.trim().length() > 0)) {
            return true;
        }
        return false;
    }

    public static void run() {
        FormBooking dialog = new FormBooking();
        dialog.pack();
        dialog.setVisible(true);
        //System.exit(0);
    }

}
