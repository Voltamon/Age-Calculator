import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO; 
import java.awt.*;
import java.awt.EventQueue;
import java.awt.image.*;
import java.awt.event.*;
import java.time.*;

public class AGE_CALCULATOR implements ActionListener
{
    JComboBox DOBY;
    JComboBox DOBM;
    JComboBox DOBD;
    
    
    JTextField AY;
    JTextField AM;
    JTextField AD;
    
    
    JTextArea dob;
    JTextArea age;
    JTextArea DOByr;
    JTextArea DOBmh;
    JTextArea DOBdy;
    ;
    JTextArea AGEyr;
    JTextArea AGEmh;
    JTextArea AGEdy;
    
    
    JFrame AGE;
    
    JButton calc;
    JButton close;
    JButton clear;
    
    long year;
    long month;
    long day;
    
    int y;
    int m;
    int d;
    
    
    LocalDate DOB;
    LocalDate today;
    
    AGE_CALCULATOR()
    {
        Integer[] day = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
        Integer[] year = {2100,2099,2098,2097,2096,2095,2094,2093,2092,2091,
                          2090,2089,2088,2087,2086,2085,2084,2083,2082,2081,
                          2080,2079,2078,2077,2076,2075,2074,2073,2072,2071,
                          2070,2069,2068,2067,2066,2065,2064,2063,2062,2061,
                          2060,2059,2058,2057,2056,2055,2054,2053,2052,2051,
                          2050,2049,2048,2047,2046,2045,2044,2043,2042,2041,
                          2040,2039,2038,2037,2036,2035,2034,2033,2032,2031,
                          2030,2029,2028,2027,2026,2025,2024,2023,2022,2021,
                          2020,2019,2018,2017,2016,2015,2014,2013,2012,2011,
                          2010,2009,2008,2007,2006,2005,2004,2003,2002,2001,
                          2000,1999,1998,1997,1996,1995,1994,1993,1992,1991,
                          1990,1989,1988,1987,1986,1985,1984,1983,1982,1981,
                          1980,1979,1978,1977,1976,1975,1974,1973,1972,1971,
                          1970,1969,1968,1967,1966,1965,1964,1963,1962,1961,
                          1960,1959,1958,1957,1956,1955,1954,1953,1952,1951,
                          1950,1949,1948,1947,1946,1945,1944,1943,1942,1941,
                          1940,1939,1938,1937,1936,1935,1934,1933,1932,1931,
                          1930,1929,1928,1927,1926,1925,1924,1923,1922,1921,
                          1920,1919,1918,1917,1916,1915,1914,1913,1912,1911,
                          1910,1909,1908,1907,1906,1905,1904,1903,1902,1901};
        Integer[] month = {1,2,3,4,5,6,7,8,9,10,11,12};
        
          
        
        DOBD = new JComboBox(day);
        DOBD.addActionListener(this);
        DOBD.setEditable(false);
        
        DOBY = new JComboBox(year);
        DOBY.addActionListener(this);
        DOBY.setEditable(false);
        DOBY.setSelectedIndex(79);
        
        DOBM = new JComboBox(month);
        DOBM.addActionListener(this);
        DOBM.setEditable(false);
        
        
        AY = new JTextField();
        AY.setEditable(false);
        AY.setText("            ");
        AY.addActionListener(this);
        
        AM = new JTextField();
        AM.setEditable(false);
        AM.setText("         ");
        AM.addActionListener(this);
        
        AD = new JTextField();
        AD.setEditable(false);
        AD.setText("         ");
        AD.addActionListener(this);
        
        
        DOByr = new JTextArea("year");
        DOByr.setEditable(false);
        DOByr.setBackground(Color.BLACK);
        DOByr.setForeground(Color.WHITE);
        
        DOBmh = new JTextArea("month");
        DOBmh.setEditable(false);
        DOBmh.setBackground(Color.BLACK);
        DOBmh.setForeground(Color.WHITE);
        
        DOBdy = new JTextArea("day");
        DOBdy.setEditable(false);
        DOBdy.setBackground(Color.BLACK);
        DOBdy.setForeground(Color.WHITE);
        
        
        AGEyr = new JTextArea("year(s)");
        AGEyr.setEditable(false);
        AGEyr.setBackground(Color.BLACK);
        AGEyr.setForeground(Color.WHITE);
        
        AGEmh = new JTextArea("month(s)");
        AGEmh.setEditable(false);
        AGEmh.setBackground(Color.BLACK);
        AGEmh.setForeground(Color.WHITE);
        
        AGEdy = new JTextArea("day(s)");
        AGEdy.setEditable(false);
        AGEdy.setBackground(Color.BLACK);
        AGEdy.setForeground(Color.WHITE);
        
        dob = new JTextArea("DOB:");
        dob.setEditable(false);
        dob.setBackground(Color.BLACK);
        dob.setForeground(Color.WHITE);
        
        age = new JTextArea("AGE:");
        age.setEditable(false);
        age.setBackground(Color.BLACK);
        age.setForeground(Color.WHITE);
        
        calc = new JButton("    CALCULATE    ");
        calc.setFocusable(false);
        calc.addActionListener(this);
        
        close = new JButton("  CLOSE  ");
        close.setFocusable(false);
        close.addActionListener(this);
        
        clear = new JButton("  CLEAR  ");
        clear.setFocusable(false);
        clear.addActionListener(this);
        
        AGE = new JFrame();
        AGE.setTitle("AGE CALCULATOR");
        AGE.setSize(345,134);
        AGE.setUndecorated(false);
        AGE.getContentPane().setBackground(Color.BLACK);
        AGE.setDefaultCloseOperation(AGE.EXIT_ON_CLOSE);
        AGE.setVisible(true);
        AGE.setResizable(false);
        AGE.setLayout(new FlowLayout());
        AGE.add(dob);
        AGE.add(DOBD);
        AGE.add(DOBdy);
        AGE.add(DOBM);
        AGE.add(DOBmh);
        AGE.add(DOBY);
        AGE.add(DOByr);
        AGE.add(clear);
        AGE.add(calc);
        AGE.add(close);
        AGE.add(age);
        AGE.add(AY);
        AGE.add(AGEyr);
        AGE.add(AM);
        AGE.add(AGEmh);
        AGE.add(AD);
        AGE.add(AGEdy);
        AGE.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent A)
    {
        if(A.getSource()==calc)
        {
            y = (int)DOBY.getSelectedItem();
            m = (int)DOBM.getSelectedItem();
            d = (int)DOBD.getSelectedItem();
            
            if((m == 4 || m == 6 || m == 9 || m == 11) && d == 31)
            {
                JOptionPane.showMessageDialog(null,"PLEASE, CHOOSE A VALID DATE","INVALID DATE",JOptionPane.ERROR_MESSAGE);            
            }
            else if(m == 2 && d >= 29)
            {
                JOptionPane.showMessageDialog(null,"PLEASE, CHOOSE A VALID DATE","INVALID DATE",JOptionPane.ERROR_MESSAGE);     
            }
            else
            {
                today = LocalDate.now();
                DOB = LocalDate.of(y,m,d);
           
                
                year = Period.between(DOB,today).getYears();
                month = Period.between(DOB,today).getMonths();
                day = Period.between(DOB,today).getDays();
                
                
                AY.setText(String.valueOf(year));
                AM.setText(String.valueOf(month));
                AD.setText(String.valueOf(day));
            }
        }
        if(A.getSource()==clear)
        {
            AY.setText("");
            AM.setText("");
            AD.setText("");
            
            DOBY.setSelectedIndex(79);
            DOBM.setSelectedIndex(0);
            DOBD.setSelectedIndex(0);
        }
        if(A.getSource()==close)
        {
            System.exit(0);
        }
    }
    public static void main()
    {
        AGE_CALCULATOR age_calc = new AGE_CALCULATOR();
    }
}
