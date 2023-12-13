
import { StyleSheet } from 'react-native';
const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#ffeeff',
      justifyContent: 'center',
      padding: "20%",
      marginBottom: "5%"
    },
    input:{
      height: 40,
      width: "100%",
      borderColor:"black",
      borderWidth:1,
      backgroundColor: "white",
      marginTop: "3%"
    },
    createForgotLink:{
      flexDirection: 'row',
      alignSelf: 'flex-end'
    },
    link:{
      color: "#2196F3",
      marginLeft: "5%",
      marginBottom:"5%",
      marginTop:"5%"

    },
    logo:{
      alignItems:"center",
      marginBottom: "15%",
      
    },
    logoName:{
      fontSize:30,
      color:"red",
      textTransform: 'uppercase',
      alignItems: "center"
  
    },
    sombra:{
      shadowColor: 'black',
      shadowOffset: { width: 5, height: 4 },
      shadowOpacity: 0.5,
      shadowRadius: 3,
      elevation: 5, 
    }    
  });

  export default styles