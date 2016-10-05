package com.example.a1436419.lab02;

import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        String loanNum=((EditText)findViewById(R.id.loanAmountTxt)).getText().toString();
        String yearNum=((EditText)findViewById(R.id.termLoanTxt)).getText().toString();
        String interestRate=((EditText)findViewById(R.id.yearlyInterestTxt)).getText().toString();

        try {
            double loanAmount = Double.parseDouble(loanNum);
            int numOfYear = Integer.parseInt(yearNum);
            double yearRate = Double.parseDouble(interestRate);

            if(yearRate > 100)
                throw new IllegalArgumentException("Error - interest rate cannot be over 100");
            if(yearRate > 25 || yearRate < 1)
                throw new IllegalArgumentException("Error - term must be between 1 and 25 years");
            LoanCalculator calculator = new LoanCalculator(loanAmount, numOfYear, yearRate);
            TextView monthlyPayment=(TextView)findViewById(R.id.monthlyPaymentTxt);
            TextView totalPayment=(TextView)findViewById(R.id.totalPaymentTxt);
            TextView totalInterest=(TextView)findViewById(R.id.totalInterestTxt);

            monthlyPayment.setText(calculator.getMonthlyPayment()+"");
            totalPayment.setText(calculator.getTotalCostOfLoan()+"");

            totalInterest.setText(calculator.getTotalInterest()+"");
        }catch(IllegalArgumentException iae){
            ((TextView)findViewById(R.id.errorMsgLbl)).setText(iae.getMessage());
        }
    }

    public void onClearText(View e){
        ((EditText) findViewById(R.id.loanAmountTxt)).setText("");
        ((EditText) findViewById(R.id.termLoanTxt)).setText("");
        ((EditText) findViewById(R.id.yearlyInterestTxt)).setText("");

        ((TextView) findViewById(R.id.monthlyPaymentTxt)).setText("");
        ((TextView) findViewById(R.id.totalPaymentTxt)).setText("");
        ((TextView) findViewById(R.id.totalInterestTxt)).setText("");
    }

}
