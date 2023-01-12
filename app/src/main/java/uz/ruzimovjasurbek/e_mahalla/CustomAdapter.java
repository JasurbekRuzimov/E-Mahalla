package uz.ruzimovjasurbek.e_mahalla;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private final Context context;
    Activity activity;
    private final ArrayList<String> user_id;
    private final ArrayList<String> ismi;
    private final ArrayList<String> familiyasi;
    private final ArrayList<String> otasining_ismi;
    private final ArrayList<String> mahallasi;
    private final ArrayList<String> jinsi;
    private final ArrayList<String> tugilgan_vaqti;
    int position;
    Animation translate_anim;



    CustomAdapter(Activity activity, Context context, ArrayList<String> user_id, ArrayList<String> ismi, ArrayList<String> familiyasi, ArrayList<String> otasining_ismi, ArrayList<String> mahallasi, ArrayList<String> jinsi, ArrayList<String> tugilgan_vaqti) {
       this.activity = activity;
        this.context = context;
        this.user_id = user_id;
        this.ismi = ismi;
        this.familiyasi = familiyasi;
        this.otasining_ismi = otasining_ismi;
        this.mahallasi = mahallasi;
        this.jinsi = jinsi;
        this.tugilgan_vaqti = tugilgan_vaqti;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        this.position = position;
        holder.user_id_txt.setText(String.valueOf(user_id.get(position)));
        holder.ism_txt.setText(String.valueOf(ismi.get(position)));
        holder.familiya_txt.setText(String.valueOf(familiyasi.get(position)));
        holder.sharifi_txt.setText(String.valueOf(otasining_ismi.get(position)));
        holder.mahalla_txt.setText(String.valueOf(mahallasi.get(position)));
        holder.jinsi_txt.setText(String.valueOf(jinsi.get(position)));
        holder.tugilganVaqti_txt.setText(String.valueOf(tugilgan_vaqti.get(position)));


        holder.mainLayout.setOnClickListener(v -> {
            Intent intent = new Intent(context, Update_Activity.class);
            intent.putExtra("id", String.valueOf(user_id.get(position)));
            intent.putExtra("ismi", String.valueOf(ismi.get(position)));
            intent.putExtra("familiyasi", String.valueOf(familiyasi.get(position)));
            intent.putExtra("otasining_ismi", String.valueOf(otasining_ismi.get(position)));
            intent.putExtra("mahallasi", String.valueOf(mahallasi.get(position)));
            intent.putExtra("jinsi", String.valueOf(jinsi.get(position)));
            intent.putExtra("tugilgan_vaqti", String.valueOf(tugilgan_vaqti.get(position)));
            activity.startActivityForResult(intent, 1);
        });

    }

    @Override
    public int getItemCount() {
        return user_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView user_id_txt, ism_txt, familiya_txt, sharifi_txt, mahalla_txt, jinsi_txt, tugilganVaqti_txt;
        LinearLayout mainLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            user_id_txt = itemView.findViewById(R.id.user_image_txt);
            ism_txt = itemView.findViewById(R.id.loginName_txt);
            familiya_txt = itemView.findViewById(R.id.familiya_txt);
            sharifi_txt = itemView.findViewById(R.id.sharifi_txt);
            mahalla_txt = itemView.findViewById(R.id.mahalla_txt);
            jinsi_txt = itemView.findViewById(R.id.jinsi_txt);
            tugilganVaqti_txt = itemView.findViewById(R.id.tugilganVaqti_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            // Animation
            translate_anim = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);

        }
    }
}
