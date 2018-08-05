% 1. Create a perceptron
net = newp([0 1; -2 2], 1); 

% 2. Set Input data P & Target data T
P = [0 1 0 1; 1 0 0 1]; 
T = [0 0 0 1];

% 3. Train the network
net = train(net, P, T); 

% 4. Run the network and get ouput Y
Y = net(P);

% 5. Display the output of the network
% The output will be equal to T
Y
