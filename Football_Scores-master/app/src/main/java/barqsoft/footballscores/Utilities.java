package barqsoft.footballscores;

/**
 * Created by yehya khaled on 3/3/2015.
 */
public class Utilities
{
    public static final int SERIE_A = 357;
    public static final int PREMIER_LEGAUE = 354;
    public static final int CHAMPIONS_LEAGUE = 362;
    public static final int PRIMERA_DIVISION = 358;
    public static final int BUNDESLIGA = 351;
    public static String getLeague(int league_num)
    {
        switch (league_num)
        {
            case SERIE_A : return "Seria A";
            case PREMIER_LEGAUE : return "Premier League";
            case CHAMPIONS_LEAGUE : return "UEFA Champions League";
            case PRIMERA_DIVISION : return "Primera Division";
            case BUNDESLIGA : return "Bundesliga";
            default: return "Not known League Please report";
        }
    }
    public static String getMatchDay(int match_day,int league_num)
    {
        if(league_num == CHAMPIONS_LEAGUE)
        {
            if (match_day <= 6)
            {
                return "Group Stages, Matchday : 6";
            }
            else if(match_day == 7 || match_day == 8)
            {
                return "First Knockout round";
            }
            else if(match_day == 9 || match_day == 10)
            {
                return "QuarterFinal";
            }
            else if(match_day == 11 || match_day == 12)
            {
                return "SemiFinal";
            }
            else
            {
                return "Final";
            }
        }
        else
        {
            return "Matchday : " + String.valueOf(match_day);
        }
    }

    public static String getScores(int home_goals,int awaygoals)
    {
        if(home_goals < 0 || awaygoals < 0)
        {
            return " - ";
        }
        else
        {
            return String.valueOf(home_goals) + " - " + String.valueOf(awaygoals);
        }
    }

    public static int getTeamCrestByTeamName (String teamname)
    {
        if (teamname==null){return R.drawable.no_icon;}
        switch (teamname)
        { //This is the set of icons that are currently in the app. Feel free to find and add more
            //as you go.
            case "1. FC Heidenheim 1846" : return R.drawable.arsenal;
            case "Manchester City FC" : return R.drawable.manchester_united;
            case "Leicester City FC" : return R.drawable.swansea_city_afc;
            case "Eintracht Frankfurt" : return R.drawable.leicester_city_fc_hd_logo;
            case "VfB Stuttgart" : return R.drawable.everton_fc_logo1;
            case "Hannover 96" : return R.drawable.west_ham;
            case "1. FSV Mainz 05" : return R.drawable.tottenham_hotspur;
            case "Hertha BSC" : return R.drawable.west_bromwich_albion_hd_logo;
            case "Borussia Dortmund" : return R.drawable.arsenal;
            case "FC Ingolstadt 04" : return R.drawable.manchester_united;
            case "FC Augsburg" : return R.drawable.swansea_city_afc;
            case "FC Schalke 04" : return R.drawable.leicester_city_fc_hd_logo;
            case "VfL Wolfsburg" : return R.drawable.everton_fc_logo1;
            case "Swansea City FC" : return R.drawable.west_ham;
            case "Crystal Palace FC" : return R.drawable.tottenham_hotspur;
            case "Aston Villa FC" : return R.drawable.west_bromwich_albion_hd_logo;
            case "Norwich City FC" : return R.drawable.arsenal;
            case "Liverpool FC" : return R.drawable.manchester_united;
            case "Watford FC" : return R.drawable.swansea_city_afc;
            case "Newcastle United FC" : return R.drawable.leicester_city_fc_hd_logo;
            case "West Bromwich Albion FC" : return R.drawable.everton_fc_logo1;
            case "SD Eibar" : return R.drawable.west_ham;
            case "Bologna FC" : return R.drawable.tottenham_hotspur;
            case "ACF Fiorentina" : return R.drawable.west_bromwich_albion_hd_logo;
            case "Rayo Vallecano de Madrid" : return R.drawable.arsenal;
            case "UD Las Palmas" : return R.drawable.manchester_united;
            case "Bayer Leverkusen" : return R.drawable.swansea_city_afc;
            case "Athletic Club" : return R.drawable.leicester_city_fc_hd_logo;
            case "Villarreal CF" : return R.drawable.everton_fc_logo1;
            case "Genoa CFC" : return R.drawable.west_ham;
            case "SS Lazio" : return R.drawable.tottenham_hotspur;
            case "RC Deportivo La Coruna" : return R.drawable.west_bromwich_albion_hd_logo;
            case "VfL Bochum" : return R.drawable.arsenal;
            case "SC Freiburg" : return R.drawable.manchester_united;
            case "1. FC Kaiserslautern" : return R.drawable.swansea_city_afc;
            case "1. FC Union Berlin" : return R.drawable.leicester_city_fc_hd_logo;
            case "SV Sandhausen" : return R.drawable.everton_fc_logo1;
            case "SC Paderborn 07" : return R.drawable.west_ham;
            case "Werder Bremen" : return R.drawable.tottenham_hotspur;
            case "Getafe CF" : return R.drawable.west_bromwich_albion_hd_logo;
            case "Arsenal London FC" : return R.drawable.arsenal;
            case "Manchester United FC" : return R.drawable.manchester_united;
            case "Swansea City" : return R.drawable.swansea_city_afc;
            case "Leicester City" : return R.drawable.leicester_city_fc_hd_logo;
            case "Everton FC" : return R.drawable.everton_fc_logo1;
            case "West Ham United FC" : return R.drawable.west_ham;
            case "Tottenham Hotspur FC" : return R.drawable.tottenham_hotspur;
            case "West Bromwich Albion" : return R.drawable.west_bromwich_albion_hd_logo;
            case "Sunderland AFC" : return R.drawable.sunderland;
            case "Stoke City FC" : return R.drawable.stoke_city;
            default: return R.drawable.no_icon;
        }
    }
}
