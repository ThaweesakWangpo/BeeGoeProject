package com.example.beegoeproject;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Information extends AppCompatActivity {



    private Spinner genderSpin, breedSpin;
    private Button saveBut;
    private EditText nameText,weightText,lengthText ;
    private String breedText,genderText;
    public static  String sentDogNameIn="",sentDogBirhtadayIn="",sentDogwieghtIn="",sentDogGenderIn="",sentDogBreedIn="",sentDogBloodIn="",sentuid="";
    private EditText birthdayText;
    DatePickerDialog.OnDateSetListener setListener;

    static  String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        getSupportActionBar().setTitle("My dog");

uid = MainActivity.uid;
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);


        nameText = (EditText) findViewById(R.id.dogname);

        weightText = (EditText) findViewById(R.id.weight);
        saveBut = (Button) findViewById(R.id.saveBtn);
        birthdayText = findViewById(R.id.birthdayInfo);


        //selection menu Dog's gender
        genderSpin = findViewById(R.id.gender);
        List<String> gen = new ArrayList<>();
       // gen.add(0,"Gender");
        gen.add("Male");
        gen.add("Female");

        ArrayAdapter<String> dataGender;
        dataGender = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, gen);
       dataGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        genderSpin.setAdapter(dataGender);

        genderSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Gender")){

                }
                else {
                    String item = parent.getItemAtPosition(position).toString();
                    genderText=item;
                    //Toast.makeText(parent.getContext(),"Select: "+item,Toast.LENGTH_SHORT).show();
                }
                //bloodDog = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //selection menu Dog's breed
        breedSpin = findViewById(R.id.breed);
        List<String> breedList = new ArrayList<>();
            //gen.add(0,"Gender");
        breedList.add("Affenpinscher");
        breedList.add("Afghan Hound");
        breedList.add("Ainu Dog");
        breedList.add("Airedale Terrier");
        breedList.add("Akita");
        breedList.add("Alaskan Malamute");
        breedList.add("Alpine Dachsbracke");
        breedList.add("American Bulldog");
        breedList.add("American Cocker Spaniel");
        breedList.add("American Eskimo Dog");
        breedList.add("American Foxhound");
        breedList.add("American Staffordshire Terrier");
        breedList.add("American Water Spaniel Anatolian Shepherd Dog");
        breedList.add("Andalusian Hound (Podenco Andaluz)");
        breedList.add("Anglo-Français de Petite Vénerie");
        breedList.add("Anglo-French Hound");

        breedList.add("Appenzeller Sennenhund");
        breedList.add("Ariege Pointer");
        breedList.add("Ariegeois");
        breedList.add("Armant");
        breedList.add("Artois Hound");
        breedList.add("Atlas Dog");
        breedList.add("Atlas Terrier");
        breedList.add("Australian Cattle Dog");
        breedList.add("Australian Kelpie");
        breedList.add("Australian Shepherd");
        breedList.add("Australian Silky-Haired Terrier");
        breedList.add("Australian Stumpy Tail Cattle Dog");
        breedList.add("Australian Terrier");
        breedList.add("Austrian Black and Tan Hound");
        breedList.add("Austrian Hound");
        breedList.add("Austrian Pinscher");
        breedList.add("Austrian Short-Haired Pinscher");
        breedList.add("Auvergne Pointer");

        breedList.add("Balkan Hound");
        breedList.add("Banjara Greyhound");
        breedList.add("Barbet");
        breedList.add("Basenji");
        breedList.add("Basset Bleu de Gascogne");
        breedList.add("Basset Fauve de");
        breedList.add("Bretagne");
        breedList.add("Basset Griffon Vendeen");
        breedList.add("Basset Hound");
        breedList.add("Bassett Artesian Normand");
        breedList.add("Bavarian Mountain Dog");
        breedList.add("Beagle");
        breedList.add("Beagle Harrier");
        breedList.add("Bearded Collie");
        breedList.add("Beauce Shepherd");
        breedList.add("Beauceron");
        breedList.add("Bedlington Terrier");
        breedList.add("Belgian Griffon");
        breedList.add("Belgian Malinois");
        breedList.add("Belgian Sheendog");

        breedList.add("Belgian Sheepdog");
        breedList.add("Belgian Shepherd, Groenendael");
        breedList.add("Belgian Shepherd, Laekenois");
        breedList.add("Belgian Shepherd, Malinois");
        breedList.add("Belgian Tervuren");
        breedList.add("Bleu de Gascogne");
        breedList.add("Bergamasco");
        breedList.add("Berger de Bresse");
        breedList.add("Berger de Savoie");
        breedList.add("Berger du Languedoc");
        breedList.add("Berger Picard");
        breedList.add("Bernese Hound");
        breedList.add("Bernese Mountain Dog");


        ArrayAdapter<String> dataBreed;
        dataBreed = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, breedList);
        dataBreed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        breedSpin.setAdapter(dataBreed);

        breedSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Breed")){

                }
                else {
                    String item = parent.getItemAtPosition(position).toString();
                    breedText=item;
                    //Toast.makeText(parent.getContext(),"Select: "+item,Toast.LENGTH_SHORT).show();
                }
                //bloodDog = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // Birthday Calendar
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        birthdayText.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Information.this, android.R.style.Theme_Holo_Dialog_MinWidth
                        ,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.holo_blue_dark));
                datePickerDialog.show();

            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date  = day+"/"+month+"/"+year;
                birthdayText.setText(date);
            }
        };

        birthdayText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Information.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date  = day+"/"+month+"/"+year;
                        birthdayText.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });


    // Button save
        saveBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameText.length()<=0){
                    nameText.setError("Please enter dog's name");
                }
                if(weightText.length()<=0){
                    weightText.setError("Please enter dog's weight");
                }
                if(lengthText.length()<=0){
                    lengthText.setError("Please enter dog's length");
                }
                if(birthdayText.length()<=0){
                    birthdayText.setError("Please enter dog's birthday");
                }

                //sent information to server
                if(nameText.length()>0&&genderText.length()>0&&birthdayText.length()>0
                        &&breedText.length()>0&&weightText.length()>0&&lengthText.length()>0) {

                    HttpInformation sent = new HttpInformation(nameText.getText().toString(),genderText,birthdayText.getText().toString(),breedText, weightText.getText().toString());

                    sent.execute();

                }

            }
        });

    }


//    public void pushData (String name, String gender, String birthday, String breed, String weight, String length){
//
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//        HashMap <String,Object> hashMap = new HashMap<>();
//        hashMap.put("name",name);
//        hashMap.put("name",name);
//        hashMap.put("name",name);
//        hashMap.put("name",name);
//
//
//
//
//
//        databaseReference.child("Information").push().setValue(hashMap);
//
//    }

//    public void pullData () {
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Information");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//                    DogData dogData = dataSnapshot.getValue(DogData.class);
//                    dogData.getBirthday();
//                    Log.d("test", "onDataChange: ");
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }



    // Inner class push email and password to server
    private class HttpInformation extends AsyncTask<String, Void, Void> {
        String dogName, gender,birthday,breed,weight,uid;
        String urlDog = ("http://lampang.cs.tu.ac.th:3030/dog");

        HttpInformation(String dogName, String gender, String birthday, String breed, String weight) {


            this.dogName = dogName;
            this.gender = gender;
            this.birthday = birthday;
            this.breed = breed;
            this.weight = weight;

        }

        @Override
        protected Void doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("application/json");
            final JSONObject object = new JSONObject();


            try {

                object.put("uid", uid);
                object.put("dogname", dogName);
                object.put("birthday", birthday);
                object.put("gender", gender);
                object.put("breed", breed);
                object.put("weight", weight);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            RequestBody body = RequestBody.create(mediaType, object.toString());
            final Request request = new Request.Builder()
                    .url(urlDog)
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();
            try {
                Response response = client.newCall(request).execute();

                Log.i("Monday",response.message());

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

    }

//    private class HttpInformation extends AsyncTask <String, Void, String> {
//        String name, gender, weight, uid, birthday, breed;
//
//        String urldog = ("http://lampang.cs.tu.ac.th:3030/dog");
//
//        HttpInformation( String name, String gender, String birthday, String breed, String weight) {
//
//            this.name = name;
//            this.gender = gender;
//            this.birthday = birthday;
//            this.breed = breed;
//            this.weight = weight;
//            this.uid = MainActivity.uid;
//            //  this.length = length;
//
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//            // Response response = null;
//            OkHttpClient client = new OkHttpClient().newBuilder().build();
//            MediaType mediaType = MediaType.parse("application/json");
//            JSONObject object = new JSONObject();
//
//
//            try {
//
//                object.put("uid", uid);
//                object.put("dogname", name);
//                object.put("birthday", birthday);
//                object.put("gender", gender);
//                object.put("breed", breed);
//                object.put("weight", weight);
//                //object.put("high",length);
//                Log.i("Hi","hello");
//                //  object.put("username",MainActivity.keep);
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            RequestBody body = RequestBody.create(mediaType, object.toString());
//            Request request = new Request.Builder()
//                    .url(urldog)
//                    .method("POST", body)
//                    .addHeader("Content-Type", "application/json")
//                    .build();
//
//            //Get Detail
//            client.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    e.printStackTrace();
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//
//                    if (response.isSuccessful()) {
//                        final String myResponse = response.body().string();
//                        Log.i("State", "weeTest");
//
//                        Information.this.runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//
//                                try {
//                                    JSONObject jsonObject = new JSONObject(myResponse);
//                                        dogData = myResponse;
//                                        Log.i("Dog",myResponse);
//
//                                    sentDogNameIn = jsonObject.getString("dogname");
//                                    sentDogGenderIn = jsonObject.getString("gender");
//                                    sentDogBirhtadayIn = jsonObject.getString("birthday");
//                                    sentDogwieghtIn = jsonObject.getString("weight");
//                                    sentDogBreedIn = jsonObject.getString("breed");
//
//                                    //sentDogLengthIn = jsonObject.getString("high")   ;
//                                    //Log.i("State", response.message());
//                                    MainActivity.a = false;
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                                // mTextViewResult.setText(myResponse);
//                                // sad.setText(key);
//
//
//                            }
//                        });
//                    }
//                }
//            });
//
//
//            try {
//                Response response = client.newCall(request).execute();
//                // response = client.newCall(request).execute();
//              //  Log.i("State", String.valueOf(response));
//                Log.i("response", response.message());
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//
//                Log.i("State", "e");
//            }
//
//            return null;
//            //   return response.message();
//    }
//        @Override
//        protected void onPostExecute(String result) {
//            if(result.equals("OK")&&name.length()>0&&gender.length()>0&&birthday.length()>0&&breed.length()>0&&weight.length()>0){
//
//                Toast toast = Toast.makeText ( Information.this, "Information Complete", Toast.LENGTH_LONG );
//                toast.show ( );
//
//                Intent intent = new Intent(Information.this,Display.class);
//                startActivity(intent);
//
//            }
//            else{
//
//                Toast toast = Toast.makeText ( Information.this, "Please check your information", Toast.LENGTH_LONG );
//                toast.show ( );
//
//            }
//
//
//            super.onPostExecute(result);
//        }
//
//
//
//
//    }
    // class for icon back on action bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

