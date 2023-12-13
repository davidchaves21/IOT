import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import {useState} from 'react'
import Login from './src/pages/login/Login';
import { Text, View } from 'react-native';
import CreateAccount from './src/pages/createAccount/CreateAccount';
import ForgotPassword from './src/pages/forgotPassword/ForgotPassword';
import ShoppingCart from './src/pages/shoppingCart/ShoppingCart';

import Home from './src/pages/home/Home';
import Icon from 'react-native-vector-icons/AntDesign';
import Chat from './src/pages/chat/Chat';
import WhoAmI from './src/pages/whoAmI/WhoAmI';

const App = (): JSX.Element => {
  const Stack = createNativeStackNavigator()
  const [shoppingCart, setShoppingCart] = useState([])

  return (
    <NavigationContainer>
          <Stack.Navigator >
            <Stack.Screen options={{headerShown:false}} name= "login" component={Login}></Stack.Screen>
            <Stack.Screen options={{title: 'Criar Conta', headerTintColor: '#000'}} name= "Criar Conta" component={CreateAccount}></Stack.Screen>
            <Stack.Screen options={{title: 'Recuperar Senha', headerTintColor: '#000'}} name= "Recuperar Senha" component={ForgotPassword}></Stack.Screen>
            
            <Stack.Screen options={ ({navigation}) => {
              return {
              title: "Ofertas",
              headerBackVisible: false,
              headerTitleAlign: "center",
              headerLeft: () => (
                <View style={{ flexDirection: 'row', marginRight: 10 }}>
                  <Icon onPress={() => navigation.navigate('whoAmI')} name="user" size={28}  />
                  <Icon  onPress={()=> (navigation.navigate('shoppingCart')) } name="shoppingcart" size={25} style={{ marginLeft: 10 }}></Icon>
                  

                </View>
              ),
              headerRight: () => (<Icon onPress={() => navigation.navigate('login')} name="logout" size={20} />)
                } 
              }
            } 
              name= "home" >{
                ()=>(
                  <Home shoppingCart={shoppingCart} setShoppingCart= {setShoppingCart}></Home>
                )
              }</Stack.Screen>
            <Stack.Screen options={{title: 'Home', headerTintColor: '#000'}} name= "whoAmI" component={WhoAmI}></Stack.Screen>
            <Stack.Screen options={{title: 'Cancelar', headerTintColor: '#000'}} name= "shoppingCart" component={ShoppingCart}></Stack.Screen>
            <Stack.Screen options={{title: 'Chat', headerTintColor: '#000' }} name="Chat" component={Chat}></Stack.Screen>
            </Stack.Navigator>
          
        </NavigationContainer>
  );
}

export default App