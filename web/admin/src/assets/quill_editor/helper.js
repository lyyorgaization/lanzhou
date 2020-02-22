import Parchment from 'parchment';

let config = {
    scope: Parchment.Scope.BLOCK,
    whitelist: ['mainBody'],
};
// let AlignAttribute = new Parchment.Attributor.Attribute('helper', 'class', config);
let HelperClass = new Parchment.Attributor.Class('helper', 'ql-helper', config);
// let AlignStyle = new Parchment.Attributor.Style('helper', 'text-align', config);

export {
    HelperClass
};