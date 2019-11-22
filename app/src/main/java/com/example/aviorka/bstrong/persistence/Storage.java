package com.example.aviorka.bstrong.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Trainee Table
 * Equipment Table
 * Recurrence Table
 * plan Table
 * Exercise Table
 * Database
 * Activity
 */
public class Storage extends SQLiteOpenHelper {

    //Singleton
    private static Storage instance;

    // Database Name
    public static final String DATABASE_NAME = "BStrong.db";

    //DataBase version
    public static final int DATABASE_VERSION = 1;

    //DDL - Data Definition Language
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table trainee(traineeId integer primary key," +
                "fullName text not null, " +
                "email text not null, " +
                "password text not null, " +
                "age integer not null, " +
                "height integer not null, " +
                "weight integer not null)");

        db.execSQL("create table equipment(equipmentId integer primary key," +
                "name text not null)");

        db.execSQL("create table recurrence(recurrenceId integer primary key autoincrement, " +
                "name text not null, " +
                "weeklyDays integer not null)");

        db.execSQL("create table [plan](planId integer primary key autoincrement, " +
                "equipmentId integer not null, " +
                "muscleID integer not null, " +
                "recurrenceId integer not null, " +
                "sessions integer not null, " +
                "repetitions integer not null, " +
                "description text not null, " +
                "imageResourceId text not null, " +
                "foreign key(equipmentID) references equipment(equipmentId)," +
                "foreign key(muscleID) references muscle(muscleID)," +
                "foreign key(recurrenceId) references recurrence(recurrenceId))");


        db.execSQL("create table muscle(muscleId integer primary key autoincrement," +
                "name text not null)");

        db.execSQL("create table exercise(exerciseId integer primary key autoincrement, " +
                "planId integer not null, " +
                "traineeId integer not null, " +
                "recurrenceId integer not null, " +
                "startDate text not null, " +
                "foreign key(planID) references [plan](planID)," +
                "foreign key(traineeId) references [trainee](traineeId)," +
                "foreign key(recurrenceId) references recurrence(recurrenceId))");

        //DML - Data Manipulation language
        //Insert into Equipment table
        db.execSQL("insert into equipment values(1,\"Dumbbell\")");
        db.execSQL("insert into equipment values(2,\"Bench\")");
        db.execSQL("insert into equipment values(3,\"Box\")");
        db.execSQL("insert into equipment values(4,\"Medicine Box\")");
        db.execSQL("insert into equipment values(5,\"No equipment\")");

        // metadata
        //Insert into recurrence table
        db.execSQL("insert into recurrence values(null,\"Low intensity\",1)");
        db.execSQL("insert into recurrence values(null,\"Medium intensity\",2)");
        db.execSQL("insert into recurrence values(null,\"High intensity\",3)");

        // metadata
        //Insert into muscle table
        db.execSQL("insert into muscle values(null,\"Legs\")");
        db.execSQL("insert into muscle values(null,\"Chest\")");
        db.execSQL("insert into muscle values(null,\"Back\")");
        db.execSQL("insert into muscle values(null,\"Biceps\")");
        db.execSQL("insert into muscle values(null,\"Triceps\")");
        db.execSQL("insert into muscle values(null,\"Shoulders\")");


        //Inserting exercise data
        //Dumbbell
        db.execSQL("insert into [plan] values(null,1, 1, 1, 3, 10,\"Goblet Squats – 3 sets 10 reps. Stand with feet set wider than shoulder-width and hold a dumbbell with both hands in front of your chest. Sit back into a squat, keeping the dumbbell in the same position then drive back up and repeat..\", \"dumbbell_goblet_squats\")");
        db.execSQL("insert into [plan] values(null,1, 1, 2, 3, 15,\"Dumbbell Lunges – 3 sets 15 reps On Each Leg. Stand upright with dumbbells at your side, palms facing your body. Lunge forward as far as you can with your right leg, bending your trailing knee so it almost brushes the floor. Use the heel of your right foot to push your upper body back to the starting position. Repeat with the opposite leg.\", \"dumbbell_dumbbell_lunges\")");
        db.execSQL("insert into [plan] values(null,1, 1, 3, 3, 10,\"Dumbbell Romanian Deadlifts – 3 sets 10 reps.Hold a dumbbell in each hand and stand with feet hip width Push your hips back and, keeping your lower back in its natural arch, need your torso forward, lowering until you feel a stretch in your hamstrings, bending slightly at the knees as needed. Squeeze your glutes as you come back up.\", \"dumbbell_romanian_deadlifts\")");
        db.execSQL("insert into [plan] values(null,1, 2, 1, 3, 10,\"Dumbbell Chest Press - 3 sets 10 reps. Lie flat on your back, or on a bench, with your feet flat on the ground. With a dumbbell in each hand, extend your arms directly over your shoulders, palms facing toward your feet. Squeeze your shoulder blades \", \"dumbbell_chest_press\")");
        db.execSQL("insert into [plan] values(null,1, 2, 2, 3, 15,\"Lying fly - 3 sets 15 reps. Lie down on your back on the bench and grab one dumbbell with each hand at body height, elbows just slightly arched.\", \"dumbbell_lying_fly\")");
        db.execSQL("insert into [plan] values(null,1, 2, 3, 3, 15,\"Incline reverse-grip dumbbell bench press - 3 sets 15  reps  .Keeping your palms facing up (supinated), exhale as you press the dumbbells upward and inward until your arms are almost fully extended and the dumbbells nearly touch.\", \"dumbbell_incline_reverse_grip_dumbbell_bench_press\")");
        db.execSQL("insert into [plan] values(null,1, 3, 1, 3, 15,\"Bent Over Row - 3 sets 15 reps. Grab a barbell with an overhand grip, hands slightly wider than shoulder width apart. With your legs slightly bent, keep your back perfectly straight and bend your upper body until it’s almost perpendicular to the floor.From here row the weight upwards into the lower part of your chest. Pause. And return under control to the start position.\", \"dumbbell_bent_over_row\")");
        db.execSQL("insert into [plan] values(null,1, 3, 2, 3, 15,\"Kneeling One Arm Row - 3 sets 15 reps.Place the cable on the 3rd or 4th lowest notch.Get down on your right knee and face the cable about 3 feet away.Grab the handle with your right hand, palm facing in, and let your arm hang forward.Pull the handle into your body and rotate the palm of your hand upwards.Let your arm back out and rotate your palm back as you do.Continue this motion for the desired reps and then switch hands and what knee is on the ground.\", \"dumbbell_kneeling_one_arm_row\")");
        db.execSQL("insert into [plan] values(null,1, 3, 3, 3, 18,\"Reverse Fly  - 3 sets 8 reps.Set your feet shoulder-width apart, then hinge at your hips until your torso is almost parallel with the floor. The dumbbells should hang straight down from your shoulders, with your elbows slightly bent (not fully straight) and palms facing each other.Keeping your core tight and back flat, pull your shoulder blades down and back (depress and retract), then raise your arms out to the side until your elbows are at shoulder height. Pause, then slowly return to the starting position.\", \"dumbbell_reverse_fly\")");
        db.execSQL("insert into [plan] values(null,1, 4, 1, 3, 10,\"Biceps Curl - 3 sets 10 reps. Stand holding a dumbbell in each hand with your arms hanging by your sides. Ensure your elbows are close to your torso and your palms facing forward. Keeping your upper arms stationary, exhale as you curl the weights up to shoulder level while contracting your biceps.\", \"dumbbell_biceps_curl\")");
        db.execSQL("insert into [plan] values(null,1, 4, 2, 3, 10,\"One-at-a-Time Biceps Curl - 3 sets 10 reps. Grab one dumbbell in each hand, palms facing forward.One arm at a time, raise one dumbbell by curling your elbow and lower it back down after a short pause. Alternate hands after the full motion is completed.\", \"dumbbell_one_at_a_time_biceps_curl\")");
        db.execSQL("insert into [plan] values(null,1, 4, 3, 3, 10,\"Hammer Curl - 3 sets 10 reps.Grab one dumbbell in each hand along the sides of your body, palms facing your body.Raise both dumbbells by curling your elbows and lower them down after a short pause.Keep your upper arms still throughout.\", \"dumbbell_hammer_curl\")");
        db.execSQL("insert into [plan] values(null,1, 5, 1, 2, 10,\"Two-Arms Triceps Extension - 2 sets 12 reps. Stand up and hold one dumbbell with both hands behind your head, upper arms pointing straight up.Raise the dumbbell up by curling your elbows and slowly lower it back after a short pause.Your upper arms should remain still throughout.\", \"dumbbell_two_arms_triceps_extension\")");
        db.execSQL("insert into [plan] values(null,1, 5, 2, 3, 10,\"One-Arm Triceps Extension - 3 sets 10 reps. Stand up and hold one dumbbell with one hand behind your head, elbow at a 90 degree angle, upper arm straight up.Raise the dumbbell with one hand until your arm is close to being fully extended and slowly lower it back after a short pause. Alternate after a set.Keep your upper arm still throughout\", \"dumbbell_one_arm_triceps_extension\")");
        db.execSQL("insert into [plan] values(null,1, 5, 3, 3, 8,\"Triceps Kickback - 3 sets 10 reps. Put your knee and hand on a bench and grab a dumbbell with your other hand, palm facing your body, upper arm parallel to your body.Push the dumbbell back by extending your elbow and allow it to slowly return after a short pause.Keep your upper arm still throughout.\", \"dumbbell_triceps_kickback\")");
        db.execSQL("insert into [plan] values(null,1, 6, 1, 3, 8,\"Palms-In Shoulder Press - 3 sets 8 reps. Stand up and hold two dumbbells at shoulder level, palms facing each other.Push the dumbbells straight up until your elbows come close to locking and lower them back down after a short pause.Be careful NOT to jerk your back in an effort to help you raise the dumbbells.\", \"dumbbell_palms_in_shoulder_press\")");
        db.execSQL("insert into [plan] values(null,1, 6, 2, 3, 8,\"Back Supported Palms-In Shoulder Press - 3 sets 8 reps.Sit on a bench (or chair) and hold two dumbbells at shoulder level, palms facing each other.Push the dumbbells straight up until your elbows come close to locking and lower them back down after a short pause.Be careful NOT to jerk your back in an effort to help you raise the dumbbells.\", \"dumbbell_back_supported_palms_in_shoulder_press\")");
        db.execSQL("insert into [plan] values(null,1, 6, 3, 3, 8,\"Palms-In Alternated Shoulder Press - 3 sets 8 reps. Stand up and hold two dumbbells, one at shoulder level and the other high with your arm extended, palms facing each other.Push one dumbbell straight up until your elbow comes close to locking and lower it back down after a short pause. Alternate hands.Be careful NOT to jerk your back in an effort to help you raise the dumbbells.\", \"dumbbell_palms_in_alternated_shoulder_press\")");


        //Bench
        db.execSQL("insert into [plan] values(null,2, 1, 1, 2, 15,\"One-legged Calf Raises - 2 sets 15 reps.  Stand lunge-length in front of a bench. Rest the top of your left foot on the bench behind you. Lower your body until your rear knee nearly touches the floor and your front thigh is parallel to the floor\", \"one-legged calf raises\")");
        db.execSQL("insert into [plan] values(null,2, 1, 2, 3, 8,\"Leg science - 3 sets 8 reps. Stand facing away from the bench, holding a barbell across your upper back. Have one leg resting on the bench behind you, laces down.Squat with your standing leg until the knee of your trailing leg almost touches the floor.Push up through your front foot to return to the start position.\", \"leg science\")");
        db.execSQL("insert into [plan] values(null,2, 1, 3, 3, 8,\"Jump over - 3 sets 8 reps . Stand infront the bench and then jump forward . Repete.\", \"jump over\")");
        db.execSQL("insert into [plan] values(null,2, 2, 1, 3, 15,\"Push-up - 3 sets 15 reps. Put your legs on the bench and your hends on the flor and do push-up\", \"push up\")");
        db.execSQL("insert into [plan] values(null,2, 2, 2, 3, 8,\"Push-up - 3 sets 8 reps. Put your hands on the bench and your legs on the flor and do push-up\", \"push up\")");
        db.execSQL("insert into [plan] values(null,2, 2, 3, 3, 15,\"Push-up - 2 sets 15 reps. Put your hands on the bench and your legs on the flor and do push-up\", \"push up\")");
        db.execSQL("insert into [plan] values(null,2, 3, 1, 3, 15,\"Open arms - 3 sets 15 reps. Laydown on the bench and open your hands to the side and closh .\", \"push up\")");
        db.execSQL("insert into [plan] values(null,2, 3, 2, 3, 15,\"Superman - 3 sets 15 reps. Lie on your stomach on the bench and slowly, lift both your arms and legs simultaneously, as much as possible. Hold this position for as long as you comfortably can and keep looking straight ahead.\", \"bench_superman\")");
        db.execSQL("insert into [plan] values(null,2, 3, 3, 3, 15,\"Aquaman - 3 sets 15 reps.  first lift your right arm and the left leg as much as you can. And when you bring the two down, lift your left arm and your right leg. Do both these movements as fast as possible. \", \"bench_aquaman\")");
        db.execSQL("insert into [plan] values(null,2, 4, 1, 3, 10,\"Incline bottle of water  Hammer Curl - 3 sets 10 reps. Sit on an incline bench and hold a bottle in each hand by your side. Keep your upper arm still and your palms facing inwards, and lift the bottle to your shoulders. Bring it back down to your side and repeat\", \"bench_incline_bottle_of_water_hammer_curl\")");
        db.execSQL("insert into [plan] values(null,2, 4, 2, 3, 10,\"Incline Inner-Bicep Curl - 3 sets 10 reps. Have bench at 45° angle. Palms facing inward with light weight. Curl upward keeping palms facing inward.\", \"bench_incline_inner_bicep_curl\")");
        db.execSQL("insert into [plan] values(null,2, 4, 3, 3, 10,\"Decline dumbblle curl - 3 sets 10 reps.  Sit on an incline bench and hold a bottle in each hand by your side. Keep your upper arm still and your palms facing inwards, and lift the bottle to your shoulders. Bring it back down to your side and repeat\", \"bench_decline_dumbblle_curl\")");
        db.execSQL("insert into [plan] values(null,2, 5, 1, 3, 10,\"Benc-dips - 3 sets 10 reps.n Stand facing away from a bench, grab it with both hands at shoulder-width. Extend your legs out in front of you. Slowly lower your body by flexing at the elbows until your arm at forearm create a 90 degree angle. Using your triceps lift yourself back to the starting position.\", \"bench_bench_dips\")");
        db.execSQL("insert into [plan] values(null,2, 5, 2, 3, 10,\"Diamond press-up - 3 sets 10 reps. Get in a press-up position and place your hands together so your index fingers and thumbs form a diamond on the bench . Keep your back straight as you lower until your chest almost touches the floor then push back up to the start position.\", \"bench_diamond_press_up\")");
        db.execSQL("insert into [plan] values(null,2, 5, 3, 3, 8,\"45-degree incline dumbbell chest press - 3 sets 8 reps. Lie back on a bench set to a 45-degree angle and lift the weights up to shoulder height, palms facing away from you. Breathe out as you press up with both arms. Lock out your arms and squeeze your chest before returning slowly to the start position.\", \"bench_45_degree_incline_dumbbell_chest_press\")");
        db.execSQL("insert into [plan] values(null,2, 6, 1, 3, 10,\"Seated bottle of wather  shoulder press - 3 sets 10 reps. Sit on the bench holding two bottle at shoulder height with an overhand grip. Press the weights up above your head until your arms are fully extended. Return slowly to the start position.\", \"bench_seated bottle of wather_shoulder_press\")");
        db.execSQL("insert into [plan] values(null,2, 6, 2, 3, 10,\"Arnold press - 3 sets 10 reps. Sit on a bench with bottle held in front of you, palms facing your shoulders as though you've just finished a bicep curl. Push the bottle up over your head while rotating your arms until your palms face away from you. Straighten your arms, pause, then reverse the movement.\", \"bench_arnold_press\")");
        db.execSQL("insert into [plan] values(null,2, 6, 3, 3, 10,\"Seated bent over rear delt fly - 3 sets 10 reps. Sit down, lean forward and hold a bottle in either hand so that they're resting above your feet. Stay bent forward as you raise your arms to the side, lining the bottle with your shoulders. Bring the weights back down and repeat.\", \"bench_seated_bent_over_rear_delt_fly\")");


        //Box
        db.execSQL("insert into [plan] values(null,3, 1, 1, 3, 10,\"Box step-up tips - 3 sets 10 reps. Step your foot on the box, knee slightly out and over the ankle.Drive up through your heel by tucking your ribs and squeezing your butt.Stand tall, then return to floor by leaning your chest forward to counterbalance body weight.On the return, drag your back foot against the box for stability\", \"box_step_up_tips\")");
        db.execSQL("insert into [plan] values(null,3, 1, 2, 3, 10,\"Box calf raises - 3 sets 10 reps. Stand with your feet slightly narrower than shoulder-width apart.Adjust your foot so both heels are off the box. Shift weight to the balls of your feet.Rise to your tiptoes.Hold it for 2 seconds at the top.Then, lower down until heel is below box height.Hold stretch for 2 seconds, then drive back up to your tiptoes\", \"box_calf_raises\")");
        db.execSQL("insert into [plan] values(null,3, 1, 3, 3, 12,\"Depth jump plus jump - 3 sets 12 reps. Start by standing upright on the box.Step off the bench with your dominant foot. (Note: This needs to be a step, not a jump.)Land on the ground with both feet at the same time.As soon as you land on the ground, explode vertically as high as you can.Absorb the impact of the landing by pushing hips back and bending knees.\", \"box_depth_jump plus_jump\")");
        db.execSQL("insert into [plan] values(null,3, 2, 1, 3, 10,\"Incline pushup - 3 sets 10 reps. Stand in front of the bench. Place the hands shoulder-width apart on the edge of the bench.Adopt a plank position by extending the legs backward until the legs and back form a straight line. Keep the weight on the balls of the feet.Slowly bend the arms to lower the chest toward the bench. Remember to keep the elbows and arms close to the body.Slowly push the body away from the bench, extending the arms but maintaining a slight bend in the elbow\", \"box_incline_pushup\")");
        db.execSQL("insert into [plan] values(null,3, 2, 2, 3, 10,\"Increase pushup - 3 sets 10 reps. . Place the legs  on the edge of the benchKeep Slowly bend the arms to lower the chest toward the bench. Perform a standard press-up, but with your hands placed wider than shoulder-width apart\", \"box_increase_pushup\")");
        db.execSQL("insert into [plan] values(null,3, 2, 3, 3, 8,\"Incline Plyo Push-Upp - 3 sets 8 reps.Start in a plank position with palms shoulder-width apart on the edge of the box and feet wide, forming a straight line from heels to head.Bend at the elbows to lower into a push-up position. Press through the palms to explode, pushing chest off the box and fully extending arms.Land softly with palms back on the edge of the box, immediately bending elbows to lower chest with control into the next push-up\", \"box_incline_plyo_push_upp\")");
        db.execSQL("insert into [plan] values(null,3, 3, 1, 3, 8,\"Supinated box row - 3 sets 8 reps . Stand erect while holding a box with a supinated grip (palms facing up). Bend your knees slightly and bring your torso forward, by bending at the waist, while keeping the back straight until it is almost parallel to the floor. \", \"box_supinated_box_row\")");
        db.execSQL("insert into [plan] values(null,3, 3, 2, 2, 12,\"Supinated box row - 2 sets 12 reps . Stand erect while holding a box with a supinated grip (palms facing up). Bend your knees slightly and bring your torso forward, by bending at the waist, while keeping the back straight until it is almost parallel to the \", \"box_supinated_box_row\")");
        db.execSQL("insert into [plan] values(null,3, 3, 3, 2, 15,\"Supinated barbell row - 2 sets 15 reps . Stand erect while holding a box with a supinated grip (palms facing up). Bend your knees slightly and bring your torso forward, by bending at the waist, while keeping the back straight until it is almost parallel to the \", \"box_supinated_box_row\")");
        db.execSQL("insert into [plan] values(null,3, 4, 1, 3, 10,\"Box biceps curl - 3 sets 10 reps.Grip the box with hands about shoulder-width distance apart while standing straight.With arms extended and resting on your thighs, curl the barbell as high as you can while contracting your biceps. Exhale during this portion of the exercise.Then, lower the barbell back down to the starting position.\", \"box_supinated_box_row\")");
        db.execSQL("insert into [plan] values(null,3, 4, 2, 3, 8,\"Box biceps curl - 3 sets 8 reps.Grip the box with hands about shoulder-width distance apart while standing straight.With arms extended and resting on your thighs, curl the barbell as high as you can while contracting your biceps. Exhale during this portion of the exercise.\", \"box_supinated_box_row\")");
        db.execSQL("insert into [plan] values(null,3, 4, 3, 2, 15,\"Box biceps curl - 2 sets 15 reps.Grip the box with hands about shoulder-width distance apart while standing straight.With arms extended and resting on your thighs, curl the barbell as high as you can while contracting your biceps. Exhale during this portion of the exercise.\", \"box_supinated_box_row\")");
        db.execSQL("insert into [plan] values(null,3, 5, 1, 3, 12,\"Triceps Dip - 3 sets 12 reps. A. Sit on the edge of a box with hands on the edge next to hips, fingers facing feet Walk feet out until legs are straight, balance on heels, and straighten arms to lift hips off the box to start.Bend elbows straight backward to lower body until shoulders are in line with elbows.press into palms to straighten arms and return to starting position\", \"box_triceps_dip\")");
        db.execSQL("insert into [plan] values(null,3, 5, 2, 2, 10,\"Triceps Dip - 2 sets 10 reps. A. Sit on the edge of a box with hands on the edge next to hips, fingers facing feet Walk feet out until legs are straight, balance on heels, and straighten arms to lift hips off the box to start.Bend elbows straight backward to lower body until shoulders are in line with elbows.press into palms to straighten arms and return to starting position\", \"box_triceps_dip\")");
        db.execSQL("insert into [plan] values(null,3, 5, 3, 3, 15,\"Triceps Dip - 2 sets 15 reps. A. Sit on the edge of a box with hands on the edge next to hips, fingers facing feet Walk feet out until legs are straight, balance on heels, and straighten arms to lift hips off the box to start.Bend elbows straight backward to lower body until shoulders are in line with elbows.press into palms to straighten arms and return to starting position\", \"box_triceps_dip\")");
        db.execSQL("insert into [plan] values(null,3, 6, 1, 3, 8,\"Shoulder push up - 3 sets 8 reps . In a push-up position, place your feet on an exercise box behind you and your hands on the floor in front of you. Straighten your legs, driving your hips into the air while. Without changing your body posture, bend your elbows 90 degrees, lowering your body until your head nearly touches the floor. Keep the 90-degree angle at your hips. Hold and push back up into starting position.\", \"box_shoulder_push_up\")");
        db.execSQL("insert into [plan] values(null,3, 6, 2, 3, 8,\"Pike hand stand - 3 sets 8 secound .  In a push-up position, place your feet on an exercise box behind you and your hands on the floor in front of you and stay for 8 secound \", \"box_pike_hand_stand\")");
        db.execSQL("insert into [plan] values(null,3, 6, 3, 3, 4,\"Shoulder stand - 2 sets 4 reps. Stand with your hands on the box for 5 secound .\", \"box_shoulder_stand\")");



        //Medicine ball
        db.execSQL("insert into [plan] values(null,4, 1, 1, 3, 10,\"Bridge With Medicine Ball Squeeze - 3 sets 10 reps Lie faceup with your knees bent and feet flat on floor, heels close to your glutes.Place the medicine ball between your knees and rest your hands along the sides of your body with your palms facing up.Squeeze the ball and drive through your heels to lift your hips.Pause at top, and return your hips to the mat\", \"medicine_ball_bridge_with_medicineball_squeeze\")");
        db.execSQL("insert into [plan] values(null,4, 1, 2, 2, 12,\"Reverse Lunge Pass Under -  2 sets 12 reps. Stand holding the medicine ball at your chest.Take a big step back with your right foot, bending both knees until your left thigh is parallel to the ground.Lower the medicine ball and pass it under your left leg, starting at the center of your body and moving toward the outside of your left leg.Return to standing and immediately repeat with your opposite leg, alternating sides with each rep\", \"medicine_ball_reverse_lunge_pass_under\")");
        db.execSQL("insert into [plan] values(null,4, 1, 3, 3, 8,\"Walking Lunge, Twist, Slam - 3 sets 8 reps. Stand tall, holding the medicine ball at your chest.Take a big step forward with your right foot, bending both knees until your right thigh is parallel to the ground.Twist over your right leg and slam the ball to the outside of your right foot.Catch the ball and return to standing, alternate sides with each rep.\", \"medicine_ball_walking_lunge_twist_slam\")");
        db.execSQL("insert into [plan] values(null,4, 2, 1, 3, 4,\"Medicine Ball Pushup -  3 sets 4 reps . Get into a standard push-up position with a medicine ball positioned underneath your chest. Instead of placing hands on the floor, place them on the medicine ball. Lower your body until your chest nearly touches the ball\", \"medicine_ball_medicine_ball_pushup\")");
        db.execSQL("insert into [plan] values(null,4, 2, 2, 3, 8,\"Single-arm Medicine Ball Pushup -  3 sets 8 reps. Start in pushup position with one hand on a medicine ball directly beneath your chest and the other hand behind your lower back. Straighten your legs behind you and spread them wide with toes pointed in the ground.\", \"medicine_ball_single_arm_medicine_ball_pushup\")");
        db.execSQL("insert into [plan] values(null,4, 2, 3, 2, 15,\"Single-arm Medicine Ball Pushup -  2 sets 15 reps. Start in pushup position with one hand on a medicine ball directly beneath your chest and the other hand behind your lower back. Straighten your legs behind you and spread them wide with toes pointed in the ground.\", \"medicine_ball_single_arm_medicine_ball_pushup\")");
        db.execSQL("insert into [plan] values(null,4, 3, 1, 3, 8,\"Medicine Ball Slams  - 3 sets 8 reps. Stand straight with your feet set shoulder width apart. Keep your shoulders back and let your arms relax.Bend over to pick up the ball.Lift the ball over your head. Slam the ball into the ground.\", \"medicine_ball_slams \")");
        db.execSQL("insert into [plan] values(null,4, 3, 2, 3, 10,\"Medicine Ball Slams  - 3 sets 10reps. Stand straight with your feet set shoulder width apart. Keep your shoulders back and let your arms relax.Bend over to pick up the ball.Lift the ball over your head. Slam the ball into the ground.\", \"medicine_ball_slams \")");
        db.execSQL("insert into [plan] values(null,4, 3, 3, 3, 10,\"Weighted Superman - 3 sets 10 sec .Draw shoulder blades down your back and peel chest and chin off the floor. Slowly raise arms and legs as high as possible, engaging core and back muscles.Hold the position at the top for a few counts while pretending you have a cape around your neck. Lower your body back onto the mat\", \"medicine_ball_weighted_superman\")");
        db.execSQL("insert into [plan] values(null,4, 4, 1, 3, 10,\"Medicine Ball Bicep Curls - 3 sets 10 reps. Stand with feet shoulder-width apart and hold a medicine ball at waist level.Keeping your elbows close to your sides, exhale while curling the ball upward to your chest.Inhale as you lower your arms back to the starting position.\", \"medicine_ball_bicep_curls\")");
        db.execSQL("insert into [plan] values(null,4, 4, 2, 3, 15,\"Medicine Ball Bicep Curls -2 sets 15 reps. Stand with feet shoulder-width apart and hold a medicine ball at waist level.Keeping your elbows close to your sides, exhale while curling the ball upward to your chest.Inhale as you lower your arms back to the \", \"medicine_ball_bicep_curls\")");
        db.execSQL("insert into [plan] values(null,4, 4, 3, 3, 15,\"Medicine Ball Bicep Curls - 3 sets 15 reps. Stand with feet shoulder-width apart and hold a medicine ball at waist level.Keeping your elbows close to your sides, exhale while curling the ball upward to your chest.Inhale as you lower your arms back to the \", \"medicine_ball_bicep_curls\")");
        db.execSQL("insert into [plan] values(null,4, 5, 1, 3, 10,\"Standing Overhead Triceps Extension - 3 sets 10 reps. Hold a medicine ball between both hands and stand tall with your back straight, shoulders relaxed, abs engaged, legs straight, and feet placed hip-width apart. Extend your arms straight overhead, holding the ball above your head.\", \"medicine_ball_standing_overhead_triceps_extension\")");
        db.execSQL("insert into [plan] values(null,4, 5, 2, 3, 15,\"Standing Overhead Triceps Extension - 2 sets 15 reps. Hold a medicine ball between both hands and stand tall with your back straight, shoulders relaxed, abs engaged, legs straight, and feet placed hip-width apart. Extend your arms straight overhead, holding the ball above your head.\", \"medicine_ball_standing_overhead_triceps_extension\")");
        db.execSQL("insert into [plan] values(null,4, 5, 3, 3, 12,\"Standing Overhead Triceps Extension - 3 sets 12 reps. Hold a medicine ball between both hands and stand tall with your back straight, shoulders relaxed, abs engaged, legs straight, and feet placed hip-width apart. Extend your arms straight overhead, holding the ball above your head.\", \"medicine_ball_standing_overhead_triceps_extension\")");
        db.execSQL("insert into [plan] values(null,4, 6, 1, 3, 10,\"Shoulder press - 3 sets 10 reps . Extend your arms to the ceiling, reaching the ball overhead, then slowly lower the ball back to the starting position\", \"medicine_ball_shoulder_press\")");
        db.execSQL("insert into [plan] values(null,4, 6, 2, 3, 12,\"Shoulder press - 2 sets 12 reps . Extend your arms to the ceiling, reaching the ball overhead, then slowly lower the ball back to the starting position\", \"medicine_ball_shoulder_press\")");
        db.execSQL("insert into [plan] values(null,4, 6, 3, 3, 15,\"Shoulder press - 2 sets 15 reps . Extend your arms to the ceiling, reaching the ball overhead, then slowly lower the ball back to the starting position\", \"medicine_ball_shoulder_press\")");


        //No equipment
        db.execSQL("insert into [plan] values(null,5, 1, 1, 3, 10,\"Jump squats - 3 sets 10 reps . To start, place your feet apart in a position in which you will be able to jump your to highest height. With your feet in that position, drop your hips back as far as possible while pushing your knees out to have them under your hips. Jump up with a single motion, jumping as high as you can\", \"no_equipment_jump_squats\")");
        db.execSQL("insert into [plan] values(null,5, 1, 2, 3, 10,\"Bulgarian Split Squats - 3 sets 10 reps. Place your feet on a surface, be it a chair, bed, or a bench, and sink down as low as you can. Drive vertically upward so that one foot is off the floor\", \"no_equipment_bulgarian_split_squats\")");
        db.execSQL("insert into [plan] values(null,5, 1, 3, 3, 10,\"Curtsy Lunge - 3 sets 10 reps. Tuck your one leg behind the other, and with legs crossed, sink down. You will feel a deep and tight activation of your glute and outer thigh. Come back to an upright position.\", \"no_equipment_curtsy_lunge\")");
        db.execSQL("insert into [plan] values(null,5, 2, 1, 3, 10,\"Push-ups - 3 sets 10 rep . start in a plank position with your pelvis tucked in, your neck neutral, and your palms directly under your shoulders. Make sure your shoulders are rotated back and down\", \"no_equipment_push_ups\")");
        db.execSQL("insert into [plan] values(null,5, 2, 2, 3, 10,\"Diamond push up - 3 sets 10 reps. placing your hands close together until your thumbs and index fingers touch forming a “diamond” shape.\", \"no_equipment_diamond_push_up\")");
        db.execSQL("insert into [plan] values(null,5, 2, 3, 3, 4,\"Push up hold - 3 sets 4 reps. Position yourself in a plank position, supporting your body with your toes and place your hands underneath your shoulders with elbows extended. Keep your abs engage and prevent letting your hips sag.\", \"no_equipment_push_up_hold\")");
        db.execSQL("insert into [plan] values(null,5, 3, 1, 3, 10,\"Supermans -  3 sets 10 reps. Lie prone on a mat with your legs extended, ankles slightly plantarflexed, arms extended overhead with palms facing each other. Gently inhale and lower your legs and arms back towards your starting position without any movement in your low back or hip\", \"no_equipment_supermans\")");
        db.execSQL("insert into [plan] values(null,5, 3, 2, 3, 10,\"Scapular push-ups -3 sets 10 reps. Keep hands shoulder-width apart and your back straight (no rounding or slouching here) as you begin the move. When you’re ready to begin, keep your arms straight as you squeeze your shoulder blades together and apart.\", \"no_equipment_scapular_push_ups\")");
        db.execSQL("insert into [plan] values(null,5, 3, 3, 3, 5,\"Atomic Burpee - 3 sets 5 reps. Lie down on your back, keeping your legs straight with your arms bent behind your head.Do a sit-up, but instead of lowering back down to the ground, jump to a standing position.Next comes the burpee. Jump down into a push-up position, complete a push-up, jump up to a squat, and jump straight up toward the ceiling.\", \"no_equipment_atomic_burpee\")");
        db.execSQL("insert into [plan] values(null,5, 4, 1, 3, 6,\"Commando cin-ups - 3 sets 6 reps. Pull yourself up with your head to the left of the bar. Try to touch the bar with your right shoulder.Then lower yourself back down to the starting position in a controlled manner. Then, pull yourself up with your head to the right of the bar. Try to touch the bar with your left shoulder.\", \"no_equipment_commando_cin_ups\")");
        db.execSQL("insert into [plan] values(null,5, 4, 2, 3, 10,\"Commando cin-ups - 3 sets 10 reps. Pull yourself up with your head to the left of the bar. Try to touch the bar with your right shoulder.Then lower yourself back down to the starting position in a controlled manner. Then, pull yourself up with your head to the right of the bar. Try to touch the bar with your left shoulder.\", \"no_equipment_commando_cin_ups\")");
        db.execSQL("insert into [plan] values(null,5, 4, 3, 3, 10,\"Commando cin-ups - 3 sets 10 reps. Pull yourself up with your head to the left of the bar. Try to touch the bar with your right shoulder.Then lower yourself back down to the starting position in a controlled manner. Then, pull yourself up with your head to the right of the bar. Try to touch the bar with your left shoulder. \", \"no_equipment_commando_cin_ups\")");
        db.execSQL("insert into [plan] values(null,5, 5, 1, 3, 10,\"Pike Push-Up - 3 sets 10 reps.  hands should be shoulder-width apart. Now lift up your hips so that your body forms an upside down V. Your legs and arms should stay as straight as possible. Pause, and then push yourself back up until your arms are straight\", \"no_equipment_pike_push_up\")");
        db.execSQL("insert into [plan] values(null,5, 5, 2, 3, 10,\"Bench Dip - 3 sets 10 reps. For this exercise you will need to place a bed  behind your back.  hold on to the bench on its edge with the hands fully extended.Slowly lower your body as you inhale by bending at the elbows until you lower yourself far enough.\", \"no_equipment_benchd_ip\")");
        db.execSQL("insert into [plan] values(null,5, 5, 3, 3, 10,\"Tricep Dip - 3 sets 10 reps.our hands should be shoulder-width apart on the surface you are dipping from, with your arms straight. Squeeze your core and glutes then raise your chin and chest to keep your body tight. From there, start the move by bending your elbows. Dip down until your arms are at a 90-degree angle\", \"no_equipment_tricep_dip\")");
        db.execSQL("insert into [plan] values(null,5, 6, 1, 3, 10,\"Feet-elevated pike pushup - 3 sets 10 reps. Get into pushup position and rest your feet on a bench or box. Bend your hips, raising your butt toward the ceiling so that your torso is vertical. Lower your body to the floor until your head is between your hands. Press back up\", \"no_equipment_feet_elevated_pike_pushup\")");
        db.execSQL("insert into [plan] values(null,5, 6, 2, 3, 10,\"Crab walk - 3 sets 10 reps. suspend yourself over parallel bars and lower your body until your upper arms are parallel\", \"no_equipment_crab_walk\")");
        db.execSQL("insert into [plan] values(null,5, 6, 3, 3, 5,\"Feet-elevated pike pushup -2 sets 5 reps. Get into pushup position and rest your feet on a bench or box. Bend your hips, raising your butt toward the ceiling so that your torso is vertical. Lower your body to the floor until your head is between your hands. \", \"no_equipment_feet_elevated_pike_pushup\")");



    }

    //Singleton
    public static Storage geInstance(Context ctx) {
        if (instance == null) {
            instance = new Storage(ctx);
        }
        return instance;
    }

    //Constructor
    private Storage(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    //Insert data to chosen table
    public long insert(String table, ContentValues values){
        SQLiteDatabase db = this.getWritableDatabase();
        long newId = db.insert(table, null, values);
        db.close(); // Closing database connection
        return newId;
    }

    public void update(String table, ContentValues values, String whereClause, String[] whereArgs){
        SQLiteDatabase db = this.getWritableDatabase();
        int newId = db.update(table, values, whereClause, whereArgs);
        db.close(); // Closing database connection

    }

    //Delete data
    public void delete(String table, String whereClause, String[] whereArgs){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table, whereClause, whereArgs);
        db.close(); // Closing database connection
    }

    //TODO
    //Add Email validate and password for login activity
    public ContentValues getSingle(String sql, String[] params){
        SQLiteDatabase db = getReadableDatabase();
        String[] columnNames = null;
        if(db == null)
            return null;

        ContentValues rs = new ContentValues();
        Cursor cur = db.rawQuery(sql, params);

        columnNames = cur.getColumnNames();
        while(cur.moveToNext()){
            for(int col = 0; col < columnNames.length; col++){
                switch(cur.getType(col)){
                    case Cursor.FIELD_TYPE_INTEGER:
                        rs.put(columnNames[col], cur.getInt(col));
                        break;
                    case Cursor.FIELD_TYPE_STRING:
                        rs.put(columnNames[col], cur.getString(col));
                        break;
                    case Cursor.FIELD_TYPE_FLOAT:
                        rs.put(columnNames[col], cur.getFloat(col));
                        break;
                    case 5: //bool, datetime
                        break;
                    case Cursor.FIELD_TYPE_NULL:
                        rs.put(columnNames[col], new String(""));
                        break;
                }
            }
        }

        cur.close();
        db.close();
        return rs;
    }

    /**
     * Check if email and password matched in DB
     * @param email
     * @param pass
     * @return
     */
    public boolean checkMatchForUser(String email, String pass, ContentValues trainee){

        String sql = "select * from trainee where email = ? and password = ? ";
        String params[] = {email, pass};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.rawQuery(sql, params);

        while(cur.moveToNext()) {
            trainee.put("traineeId", cur.getInt(0));
            trainee.put("fullName", cur.getString(1));
            trainee.put("email", cur.getString(2));
            trainee.put("password", cur.getString(3));
            trainee.put("age", cur.getInt(4));
            trainee.put("height", cur.getInt(5));
            trainee.put("weight", cur.getInt(6));   // error in Avi's pc
            cur.close();
            db.close();
            return true;
        }

        cur.close();
        db.close();
        return false;
}



    public List<ContentValues> getMultiple(String sql, String[] params){
        SQLiteDatabase db = getReadableDatabase();
        String[] columnNames = null;
        if(db == null)
            return null;

        List<ContentValues> resultSet = new LinkedList<>();

        Cursor cur = db.rawQuery(sql, params);

        columnNames = cur.getColumnNames();
        while(cur.moveToNext()){
            ContentValues record = new ContentValues();
            for(int col = 0; col < columnNames.length; col++){
                switch(cur.getType(col)){
                    case 1:
                        record.put(columnNames[col], cur.getInt(col));
                        break;
                    case 2:
                        record.put(columnNames[col], cur.getString(col));
                        break;
                    case 3:
                        record.put(columnNames[col], cur.getString(col));
                        break;
                    case 4:
                        record.put(columnNames[col], cur.getFloat(col));
                        break;
                    case 5: //bool, datetime
                        break;
                    case Cursor.FIELD_TYPE_NULL:
                        record.put(columnNames[col], new String(""));
                        break;
                }
            }
            resultSet.add(record);
        }

        cur.close();
        db.close();
        return resultSet;
    }


}