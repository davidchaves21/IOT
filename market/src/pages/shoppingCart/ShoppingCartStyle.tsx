import { StyleSheet } from 'react-native';

export const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
    backgroundColor: '#fff',
  },
  item: {
    backgroundColor: '#ffeeff',
    flexDirection: 'row',
    alignItems: 'center',
    borderBottomWidth: 1,
    borderColor: '#ccc',
    paddingBottom: 8,
    marginBottom: 8,
  },
  image: {
    width: 80,
    height: 80,
    marginRight: 16,
  },
  itemDetails: {
    flex: 1,
  },
  quantityContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    marginTop: 4,
  },
  quantityButton: { 
    width: "10%",
    textAlign: "center",
    backgroundColor: '#FF999C',
    borderWidth: 1,
    borderColor: '#ccc',
    borderRadius: 3,
    paddingLeft: 5,
    paddingRight: 5,
    marginLeft: 5
  },
  quantityInput: {
    borderWidth: 1,
    borderColor: '#ccc',
    borderRadius: 5,
    paddingVertical: 2,
    paddingHorizontal: 8,
    marginLeft: 4,
    width: 40,
    backgroundColor:'white'
  },
  priceContainer: {
    flex: 1,
    alignItems: 'flex-end',
  },
  priceText: {
    fontWeight: 'bold',
  },
  totalContainer: {
    borderTopWidth: 1,
    borderColor: '#ccc',
    marginTop: 20,
    paddingTop: 8,
    alignItems: 'flex-end',
  },
  totalText: {
    fontWeight: 'bold',
    fontSize: 18,
    color: '#FF999C'
  },
  trashButton: {
    padding: 5,
    backgroundColor: '#eee',
    borderRadius: 5,
    marginLeft: 5,
  },
});
