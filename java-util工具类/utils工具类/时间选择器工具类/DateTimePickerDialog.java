package com.zhang.testdate;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *使用
 *mDTPicker = new DateTimePickerDialog(this);
 *需要哪个时间就调用哪个
 *mDTPicker.setDateFormat(DATE_FORMAT);
 *mDTPicker.setTimeFormat(TIME_FORMAT);
 *mDTPicker.dateTimePicKDialog(date, DateTimePickerDialog.TYPE.DATE);
 *
 *
 * 日期时间选择控件
 *
 * @author
 */
public class DateTimePickerDialog {
    private Activity activity;

    public void setHourLimited(boolean hourLimited) {
        isHourLimited = hourLimited;
    }

    private boolean isHourLimited = false; //整时限制。

    /**
     * 日期时间弹出选择框构
     *
     * @param activity：调用的父activity
     */
    public DateTimePickerDialog(Activity activity) {
        this.activity = activity;
    }

    public interface DateTimeChangerListener {
        void onDataTimeChange();
    }

    private DateTimeChangerListener listener;

    public void setDateChangeListener(DateTimeChangerListener l) {
        this.listener = l;
    }

    /**
     * 日期格式
     */
    private String mDateFormat = "yyyy-MM-dd";

    public void setDateFormat(String format) {
        mDateFormat = format;
    }

    private String mTimeFormat = "HH:00";

    public void setTimeFormat(String format) {
        mTimeFormat = format;
    }

    public enum TYPE {
        DATE, TIME
    }

    /**
     * 弹出日期时间选择框
     *
     * @param dateTimeTextEdite 需要设置的日期时间文本编辑框
     * @param type:             0为日期时间类型:yyyy-MM-dd HH:mm:ss
     *                          1为日期类型:yyyy-MM-dd
     *                          2为时间类型:HH:mm:ss
     * @return
     */
    public Dialog dateTimePicKDialog(final TextView dateTimeTextEdite, TYPE type) {
        Dialog dialog = null;

        Calendar c = Calendar.getInstance();
        switch (type) {
            case DATE:
                dialog = new DatePickerDialog(activity,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                                SimpleDateFormat sdf = new SimpleDateFormat(mDateFormat);

                                String beforeText = dateTimeTextEdite.getText().toString();

                                dateTimeTextEdite.setText(sdf.format(calendar.getTime()));

                                if (listener != null && !beforeText.equals(dateTimeTextEdite.getText().toString())) {
                                    listener.onDataTimeChange();
                                }
                            }
                        },
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DATE));
                dialog.show();
                break;
            case TIME:
                dialog = new TimePickerDialog(activity,
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                                Calendar calendar = Calendar.getInstance();
                                if (isHourLimited) {
                                    calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, hourOfDay,
                                            0);
                                    if (minute > 0) {
                                        Toast.makeText(activity, activity.getString(R.string.valid_pwd_hour_limit), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, hourOfDay, minute);
                                }

                                SimpleDateFormat sdf = new SimpleDateFormat(mTimeFormat);

                                String beforeText = dateTimeTextEdite.getText().toString();

                                dateTimeTextEdite.setText(sdf.format(calendar.getTime()));

                                if (listener != null && !beforeText.equals(dateTimeTextEdite.getText().toString())) {
                                    listener.onDataTimeChange();
                                }
                            }
                        },
                        c.get(Calendar.HOUR_OF_DAY),
                        c.get(Calendar.MINUTE),
                        true);
                dialog.show();
                break;
//            default:
//                LinearLayout dateTimeLayout = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.datetime, null);
//                datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
//                timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.timepicker);
//                init(datePicker, timePicker);
//                timePicker.setIs24HourView(true);
//                timePicker.setOnTimeChangedListener(this);
//
//                ad = new AlertDialog.Builder(activity).setIcon(R.drawable.ic_home).setTitle(initDateTime).setView(dateTimeLayout).setPositiveButton("设置",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog,
//                                                int whichButton) {
//                                dateTimeTextEdite.setText(dateTime);
//                            }
//                        }).setNegativeButton("取消",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog,
//                                                int whichButton) {
//                                dateTimeTextEdite.setText("");
//                            }
//                        }).show();
//
//                onDateChanged(null, 0, 0, 0);
//                return ad;
        }

        return dialog;
    }

//    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
//        onDateChanged(null, 0, 0, 0);
//    }
//
//    public void onDateChanged(DatePicker view, int year, int monthOfYear,
//                              int dayOfMonth) {
//        Calendar calendar = Calendar.getInstance();
//
//        calendar.set(datePicker.getYear(), datePicker.getMonth(),
//                datePicker.getDayOfMonth(), timePicker.getCurrentHour(),
//                timePicker.getCurrentMinute());
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
//        dateTime = sdf.format(calendar.getTime());
//        ad.setTitle(dateTime);
//    }

}