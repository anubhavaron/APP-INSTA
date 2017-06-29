package com.example.anubhav.newprojectappcollege;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ANUBHAV on 6/29/2017.
 */

public class CHAT_SCREEN extends AppCompatActivity  {

    public static final String TAG="ChatScreen";
    public static final String PERSON_NAME="PSNAM";
    public static final String CHAT_ID="chat_id";
    public static final String PHONE_NUMBER="phone_number";
    private  String receiverName;
    private int chatID;
    private String receiverNum;
    private String senderNum;
    private static CHAT_SCREEN chscreen=null;


    private ArrayList<ChatMessages> msgs=new ArrayList<>();
    private EditText etChat;
    private ImageButton btnSend;
    private RecyclerView rView;
    private ChatMsgAdapter msgAdapter;
    private LinearLayoutManager manager;
    private BroadcastReceiver receiver;
    private static CHAT_SCREEN getInstance()
    {

        return chscreen;

    }


    @Override
        protected void onCreate(Bundle savedInstancesState)
    {

        super.onCreate(savedInstancesState);
        setContentView(R.layout.chat_screen);
        /*** NotificationManager managerN= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        managerN.cancelAll();         I dont know why this is used,this line is copied from other apps***/

        chscreen=this;


        /***
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ChatMessages chat = (ChatMessages) intent.getSerializableExtra(MessageService.MSG_TO_UPDATE);
                messageReceived(chat);
            }
        };

         ***/


        Intent intent = getIntent();
        /***

         receiverName = intent.getStringExtra(PERSON_NAME);
         chatID = intent.getIntExtra(CHAT_ID, -1);
         receiverNum = intent.getStringExtra(PHONE_NUMBER);
         senderNum = getSharedPreferences(FcmId.FCM_TOKEN_SAVE, MODE_PRIVATE)
         .getString(LoginActivity.USER_PHONE, null);








         ***/
        setTitle(receiverName);

        /*** msgfiller***/
        etChat = (EditText) findViewById(R.id.chat_box);
        btnSend = (ImageButton) findViewById(R.id.btn_send);
        manager = new LinearLayoutManager(this);
        rView.setLayoutManager(manager);
        msgAdapter = new ChatMsgAdapter(msgs);
        rView.setAdapter(msgAdapter);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etChat.getText().toString().isEmpty()) {
                    sendMessage(etChat.getText().toString());
                    etChat.setText("");
                }
            }
        });








    }
    public void sendMessage(final String message)
    {


        /***ADD MSG TO ADAPTER**/
    }

    public void messageReceived(ChatMessages msg) {
        msgs.add(msg);
        msgAdapter.notifyDataSetChanged();
        rView.post(new Runnable() {
            @Override
            public void run() {
                rView.smoothScrollToPosition(msgs.size());
            }
        });

    }


    public class MyHolder extends RecyclerView.ViewHolder
    {
        private TextView tvMsg;
        private TextView tvtime;
        private ChatMessages messages;


        public MyHolder(View itemView){


            super(itemView);
            tvMsg=(TextView)itemView.findViewById(R.id.msg);
            tvtime=(TextView)itemView.findViewById(R.id.time_stamp);

        }




    }

    class ChatMsgAdapter extends RecyclerView.Adapter<MyHolder>
    {
        private ArrayList<ChatMessages> msgData = new ArrayList<>();
        public ChatMsgAdapter(ArrayList<ChatMessages> msgData) {
            this.msgData = msgData;
        }


        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = getLayoutInflater().inflate(viewType, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.messages = msgData.get(position);
            holder.tvMsg.setText(holder.messages.getMessage());
            holder.tvtime.setText(holder.messages.getTime());



        }

        @Override
        public int getItemCount() {
            return msgData.size();
        }
    }










}
